
package helloworldapp;

        import io.temporal.activity.ActivityInterface;
        import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface Api {

    @ActivityMethod
    String receiveApi(String currency,String currency_main,Integer price);
}