package ntu.khoi.d_n_c;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KetNoiMang {
    // Địa chỉ gốc của trang web cung cấp tin
    private static final String DUONG_DAN_GOC = "[https://newsapi.org/](https://newsapi.org/)";
    private static Retrofit retrofit = null;

    public static GiaoDienApi layGiaoDienApi() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(DUONG_DAN_GOC)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(GiaoDienApi.class);
    }
}