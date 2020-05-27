package de.telekom.sea.mystuff.frontend.android.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Creates API instances for performing REST calls.
 */
public class ApiFactory {


    private final Retrofit retrofit;
    @Getter
    private final String baseRestUrl;
    private final String hostName;



    public ApiFactory(
            String hostName,
            String protocol,
            int port)

 {

        this.baseRestUrl = protocol + "://" + hostName + ":" + port;
        this.hostName = hostName;


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient;
        // create OkHttp client
            okHttpClient = new OkHttpClient.Builder()
                   // .authenticator(authenticator)
                    .addInterceptor(loggingInterceptor)
                    .build();


        Gson gson = new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(this.baseRestUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .client(okHttpClient)
                .build();
    }

    /**
     * @param retrofitApiInterface defines the REST interface, must not be null
     * @param <S>
     * @return API instance for performing REST calls, never null
     */
    public <S> S createApi(Class<S> retrofitApiInterface) {
        return retrofit.create(retrofitApiInterface);
    }

    public String getBackendBaseUrl() {
        return this.baseRestUrl;
    }

}
