package configuration.gongdel.spring.domain;

public class Application {

    private ApplicationUser applicationUser;

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    @Override
    public String toString() {
        return "Application{" +
                "applicationUser=" + applicationUser +
                '}';
    }
}
