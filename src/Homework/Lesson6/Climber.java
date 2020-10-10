package Homework.Lesson6;

public class Climber {
    // Альпинист создаётся с именем (не менее 3 символов)
    // и адресом проживания (не менее 5 символов)

    private String fullName;
    private String address;

    public void setFullName(String fullName) {
        if (fullName != null && fullName.trim().length() >= 3) this.fullName = fullName;
        else throw new IllegalArgumentException("Имя не может быть короче трех символов.");
    }

    public void setAddress(String address) {
        if (address.trim().length() >= 5) this.address = address;
        else throw new IllegalArgumentException("Адрес не может быть короче пяти символов.");
    }

    public Climber(String fullName, String address) {
        setFullName(fullName);
        setAddress(address);
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
