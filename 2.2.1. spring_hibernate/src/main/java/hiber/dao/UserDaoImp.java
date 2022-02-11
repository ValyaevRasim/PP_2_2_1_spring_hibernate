package hiber.dao;

import com.fasterxml.classmate.AnnotationConfiguration;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void addCar(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public List<User> userCarsList(int series, String model) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("FROM User user " +
                "WHERE user.userCar.model = :prmModel and user.userCar.series = :prmSeries", User.class);
        query.setParameter("prmSeries", series);
        query.setParameter("prmModel", model);
        List<User> listUser = query.getResultList();
        return (listUser.isEmpty() ? null : listUser);

    }
}
