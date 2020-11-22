package Homework.lesson6.GroupFormation;

public class GroupFormation {
    public static void main(String[] args) {
        Climber climber1 = new Climber("Ivan Ivanov", "1st street");
        Climber climber2 = new Climber("Pyotr Petrov", "2nd street");
        Climber climber3 = new Climber("Vasily Vasiliev", "3rd street");
        Climber climber4 = new Climber("Nikolaj Nikolaev", "4th street");
        Climber climber5 = new Climber("Dmitrij Dmitriev", "5th street");
        Climber climber6 = new Climber("Anatolij Anatoliev", "6th street");
        Climber climber7 = new Climber("Evgenij Evgeniev", "7th street");

        Mountain kazbek = new Mountain("Kazbek", "Russia", 5033);
        Mountain beluha = new Mountain("Beluha", "Russia", 4506);
        Mountain elbrus = new Mountain("Elbrus", "Russia", 5642);

        Group group1 = new Group(3, kazbek);
        Group group2 = new Group(2, beluha);
        Group group3 = new Group(2, elbrus);

//        group1.addClimber(climber1);
//        group1.addClimber(climber2);
//        group1.addClimber(climber3);
        group1.addClimber(climber1, climber2, climber3);
        group1.addClimber();

        group2.addClimber(climber4);
        group2.addClimber(climber5);

        group3.addClimber(climber6);
        group3.addClimber(climber7);


    }
}
