
package helloworldapp;

        import io.temporal.activity.ActivityInterface;
        import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface PostApi {
    String convert = "";
    @ActivityMethod
    String postApi();
}