package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        Car car1 = new Car(1, "model1");
        Car car2 = new Car(2, "model2");
        Car car3 = new Car(3, "model3");
        Car car4 = new Car(4, "model4");

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");


//        user1.setUserCar(car1);
        car1.setUser(user1);


//        user2.setUserCar(car2);
        car2.setUser(user2);


//        user3.setUserCar(car3);
        car3.setUser(user3);

//        user4.setUserCar(car4);
        car4.setUser(user4);


        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        userService.addCar(car1);
        userService.addCar(car2);
        userService.addCar(car3);
        userService.addCar(car4);


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        List<User> userCars = userService.userCarsList(car2.getSeries(), car2.getModel());
        for (User user : userCars) {
            System.out.println("\nuserCars cars = " + user.getUserCar());
        }

        context.close();
    }
}
