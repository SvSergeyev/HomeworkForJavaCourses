package Homework.Lesson6;

public class Climber {
    // Альпинист создаётся с именем (не менее 3 символов) и адресом проживания (не менее 5 символов)

    // поля класса:
    private String fullName;
    private String address;

    // гет/сет:
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName == null || fullName.trim().length() < 3)
            throw new IllegalArgumentException("Длинна имени не может быть меньше 3-х символов");
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().length() < 3)
            throw new IllegalArgumentException("Адрес должен быть длиннее 5-и символов");
        this.address = address;
    }

    // конструкторы:
    public Climber() {
        fullName = "";
        address = "";
    }

    public Climber(String fullName) {
        setFullName(fullName);
        address = "";
    }

    public Climber(String fullName, String address) {
        setFullName(fullName);
        setAddress(address);
    }

    // методы:
    public void getClimberInfo() {
        System.out.println("Full name: " + fullName + ", address: " + address);
    }

    // переопределенные методы:
    @Override
    public String toString() {
        return "Climber{" +
                "fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
