package org.mealsapp.transport;

/**
 * {@link MealDto} is the main entity that describes the properties of the meal that are displayed.
 */
public final class MealDto {
  /** The name of the meal. */
  private String strMeal;

  /** The category of the meal. */
  private String strCategory;

  /** The area of origin of the meal. */
  private String strArea;

  /** The instructions on how to prepare the meal. */
  private String strInstructions;

  public MealDto() {
  }

  public MealDto(String strMeal, String strCategory, String strArea, String strInstructions) {
    this.strMeal = strMeal;
    this.strCategory = strCategory;
    this.strArea = strArea;
    this.strInstructions = strInstructions;
  }

  public String getStrMeal() {
    return strMeal;
  }

  public void setStrMeal(String strMeal) {
    this.strMeal = strMeal;
  }

  public String getStrCategory() {
    return strCategory;
  }

  public void setStrCategory(String strCategory) {
    this.strCategory = strCategory;
  }

  public String getStrArea() {
    return strArea;
  }

  public void setStrArea(String strArea) {
    this.strArea = strArea;
  }

  public String getStrInstructions() {
    return strInstructions;
  }

  public void setStrInstructions(String strInstructions) {
    this.strInstructions = strInstructions;
  }

  @Override
  public String toString() {
    return "Meal{" + "strMeal='" + strMeal + '\'' + ", strCategory='" + strCategory + '\'' + ", strArea='" + strArea + '\'' + ", strInstructions='" + strInstructions + '\'' + '}';
  }

}
