package com.upc.fullfeedbackend.services;

import com.upc.fullfeedbackend.models.Meal;
import com.upc.fullfeedbackend.models.NutritionalPlan;
import com.upc.fullfeedbackend.models.Patient;
import com.upc.fullfeedbackend.models.Preferences;
import com.upc.fullfeedbackend.models.api.ApiAlternativesRequest;
import com.upc.fullfeedbackend.models.api.ApiRequest;
import com.upc.fullfeedbackend.models.api.Dish;
import com.upc.fullfeedbackend.models.dto.ConsumedBalanceMapSQL;
import com.upc.fullfeedbackend.models.dto.ConsumedBalanceResponseDTO;
import com.upc.fullfeedbackend.repositories.MealRespository;
import io.swagger.models.auth.In;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class MealService {

    @Autowired
    MealRespository mealRespository;

    @Autowired
    NutritionalPlanService nutritionalPlanService;

    @Autowired
    PatientService patientService;

    @Autowired
    PatientPreferencesService patientPreferencesService;


    public List<Meal> getMeals(){
        return null;
    }

    public Meal getMealByID(Long mealId){
        return mealRespository.findById(mealId).get();
    }

    public Meal saveMeal(Meal meal){
        return mealRespository.save(meal);
    }

    public List<Meal> generateMonthMealsForPatient(Long patientId, Integer calories, Integer weight){

        String url = "https://fullfeed-algorithm.azurewebsites.net/diet-month";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Patient patient = patientService.getPatientById(patientId);

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setCalories(calories);
        apiRequest.setWeight(weight);
        apiRequest.setRegion(patient.getRegion().getName());
        List<String> allergies = new ArrayList<>();
        List<String> favorites = new ArrayList<>();
        List<Preferences> preferencesAllergies = patientPreferencesService.findAllergiesByPatient(patientId);
        List<Preferences> preferencesFavorites = patientPreferencesService.findFavoritesByPatient(patientId);
        for (Preferences allergy : preferencesAllergies){
            allergies.add(allergy.getName());
        }
        for (Preferences favorite : preferencesFavorites){
            favorites.add(favorite.getName());
        }
        apiRequest.setAllergies(allergies);
        apiRequest.setFavorites(favorites);

        HttpEntity<ApiRequest> httpEntity = new HttpEntity<>(apiRequest, headers);

        System.out.println(httpEntity);

        Dish[][] dishes = restTemplate.postForObject(url, httpEntity, Dish[][].class);

        List<Meal> meals = new ArrayList<>();

        NutritionalPlan nutritionalPlan = nutritionalPlanService.getActiveNutritionalPlanByPatientId(patientId);

        int indexDay = 1;
        for (Dish[] dishList: dishes) {
            for (Dish dish: dishList) {

                Meal meal = new Meal();
                meal.setSchedule(dish.getHorario());
                meal.setName(dish.getNombre());
                meal.setProtein(dish.getProteinas());
                meal.setFat(dish.getGrasas());
                meal.setCarbohydrates(dish.getCarbohidratos());
                meal.setNutritionalPlan(nutritionalPlan);
                meal.setStatus((byte) 0);

                String result = String.join("-", dish.getIngredientes());
                meal.setIngredients(result);

                meal.setTotalCalories(dish.getCalorias_totales());
                meal.setGramsPortion(dish.getPorcion_gramos());

                meal.setDay(UtilService.getNowDateMealsWhitAddDays(indexDay));

                meals.add(meal);
            }
            indexDay++;
        }

        return mealRespository.saveAll(meals);
    }

    public List<Meal> getMealsByDay(Date date, Long patientId){
        List<Meal> meals = mealRespository.findByDayAndNutritionalPlan_PersonalTreatments_Patient_PatientIdAndNutritionalPlan_IsActive(date, patientId);
        return meals;
    }

    public List<Meal> getMealsBetweenDatesAndNutritionalPlan(Date startDate, Date endDate, NutritionalPlan nutritionalPlanService){
        return mealRespository.findByDayIsGreaterThanEqualAndDayIsLessThanEqualAndNutritionalPlan(startDate, endDate, nutritionalPlanService);
    }

    public Integer getCountOfSuccessfulDaysByPatient(Long patientId){
        Integer successfulDays = mealRespository.countByStatusAndNutritionalPlan_PersonalTreatments_Patient_PatientId((byte) 1, patientId);
        if (successfulDays != null) return successfulDays;
        return 0;
    }

    public List<Meal> generateAlternativesMeal(ApiAlternativesRequest request){
        String url = "https://fullfeed-algorithm.azurewebsites.net/alternatives";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        request.setCalories(request.getCalories());
        request.setSchedule(request.getSchedule());

        HttpEntity<ApiAlternativesRequest> httpEntity = new HttpEntity<>(request, headers);

        Dish[] dishes = restTemplate.postForObject(url, httpEntity, Dish[].class);

        List<Meal> meals = new ArrayList<>();
        for (Dish dish: dishes) {
            Meal meal = new Meal();
            meal.setName(dish.getNombre());
            meal.setProtein(dish.getProteinas());
            meal.setFat(dish.getGrasas());
            meal.setCarbohydrates(dish.getCarbohidratos());
            meal.setStatus((byte) 0);
            meal.setTotalCalories(dish.getCalorias_totales());
            meal.setGramsPortion(dish.getPorcion_gramos());
            meal.setSchedule(dish.getHorario());

            String result = String.join("-", dish.getIngredientes());
            meal.setIngredients(result);
            meal.setImageUrl("");
            meals.add(meal);
        }


        return meals;
    }

    public List<ConsumedBalanceResponseDTO> getConsumedBalanced(Long patientId, Date startDate, Date endDate){
        List<ConsumedBalanceMapSQL> resultSQl = mealRespository.getConsumeBalance(patientId, startDate, endDate);
        List<ConsumedBalanceResponseDTO> response = new ArrayList<>();
        for (ConsumedBalanceMapSQL item: resultSQl) {
            response.add(new ConsumedBalanceResponseDTO(item.getDate(), item.getFat(), item.getCarbohydrates(),item.getProtein()));
        }
        return response;
    }

    public Integer getFirstDayOfWeekMeal(Long patientId){
        Calendar c = Calendar.getInstance();
        Date dt = mealRespository.getFirstDayWeekOfDiet(patientId);
        if (dt == null) return 0;
        c.setTime(dt);
        Integer day = c.get(Calendar.DAY_OF_WEEK);
        day--;
        if (day < 1) day = 7;
        return day;
    }

    private Date getDate(int diaMas){
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, diaMas);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        dt = c.getTime();
        return dt;
    }

}
