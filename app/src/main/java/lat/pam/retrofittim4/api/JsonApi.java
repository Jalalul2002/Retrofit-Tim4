package lat.pam.retrofittim4.api;

import java.util.List;

import lat.pam.retrofittim4.model.PostModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApi {

    @GET ("posts")
    Call<List<PostModel>> getPosts();
}
