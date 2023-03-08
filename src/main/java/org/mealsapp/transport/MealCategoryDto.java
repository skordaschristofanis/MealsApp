package org.mealsapp.transport;

import java.util.Objects;

public final class MealCategoryDto {

  private String strCategory;
  private String strCategoryThumb;
  private String strCategoryDescription;

  public MealCategoryDto() {
  }

  public MealCategoryDto(String strCategory) {
    this.strCategory = strCategory;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    MealCategoryDto that = (MealCategoryDto) o;

    if (!Objects.equals(strCategory, that.strCategory)) return false;
    if (!Objects.equals(strCategoryThumb, that.strCategoryThumb))
      return false;
    return Objects.equals(strCategoryDescription, that.strCategoryDescription);
  }

  @Override
  public int hashCode() {
    int result = strCategory != null ? strCategory.hashCode() : 0;
    result = 31 * result + (strCategoryThumb != null ? strCategoryThumb.hashCode() : 0);
    result = 31 * result + (strCategoryDescription != null ? strCategoryDescription.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "MealCategoryDto{" + "strCategory='" + strCategory + '\'' + ", strCategoryThumb='" + strCategoryThumb + '\'' + ", strCategoryDescription='" + strCategoryDescription + '\'' + '}';
  }

  public String getStrCategory() {
    return strCategory;
  }

  public void setStrCategory(String strCategory) {
    this.strCategory = strCategory;
  }

  public String getStrCategoryThumb() {
    return strCategoryThumb;
  }

  public void setStrCategoryThumb(String strCategoryThumb) {
    this.strCategoryThumb = strCategoryThumb;
  }

  public String getStrCategoryDescription() {
    return strCategoryDescription;
  }

  public void setStrCategoryDescription(String strCategoryDescription) {
    this.strCategoryDescription = strCategoryDescription;
  }
}
