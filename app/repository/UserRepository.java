package repository;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.Expr;
import models.User;
import play.db.ebean.EbeanConfig;
import play.db.ebean.EbeanDynamicEvolutions;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class UserRepository {

    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext databaseExecutionContext;
    private final EbeanDynamicEvolutions ebeanDynamicEvolutions;

    @Inject
    public UserRepository(EbeanConfig ec, DatabaseExecutionContext dec, EbeanDynamicEvolutions ede) {
        ebeanServer = Ebean.getServer(ec.defaultServer());
        databaseExecutionContext = dec;
        ebeanDynamicEvolutions = ede;
    }

    public User create(User user) {
        ebeanServer.insert(user);
        return user;
    }

    public User getUserByEmail(String email) {
        User user = ebeanServer.createQuery(User.class).where().add(Expr.eq("email", email)).findUnique();
        return user;
    }
}
