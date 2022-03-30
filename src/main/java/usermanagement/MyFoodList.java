package usermanagement;

import java.time.LocalDate;

public class MyFoodList {

    private int id ;
    private String foodName;

    @Override
    public String toString() {
        return "MyFoodList{" +
                "id=" + id +
                ", foodName='" + foodName + '\'' +
                ", foodDate=" + foodDate +
                ", iduser=" + iduser +
                '}';
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    private LocalDate foodDate;
    private int iduser;

    public MyFoodList(String foodName, LocalDate foodDate, int iduser) {
        this.foodName = foodName;
        this.foodDate = foodDate;
        this.iduser = iduser;
    }

    public MyFoodList() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public LocalDate getFoodDate() {
        return foodDate;
    }

    public void setFoodDate(LocalDate foodDate) {
        this.foodDate = foodDate;
    }
}
