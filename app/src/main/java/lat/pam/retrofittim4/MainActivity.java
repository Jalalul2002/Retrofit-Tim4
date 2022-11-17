package lat.pam.retrofittim4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import lat.pam.retrofittim4.api.JsonApi;
import lat.pam.retrofittim4.model.PostModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String BASE_URL = "https://jsonplaceholder.typicode.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonApi jsonApi = retrofit.create(JsonApi.class);

        textViewResult = findViewById(R.id.text_view_result);

        Call<List<PostModel>> call = jsonApi.getPosts();

        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                }

                List<PostModel> posts = response.body();

                for (PostModel post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}