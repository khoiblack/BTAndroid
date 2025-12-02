package ntu.khoi.d_n_c;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GiaoDienApi {
    @GET("v2/top-headlines")
    Call<PhanHoiTinTuc> layTinTucMoiNhat(
            @Query("country") String maQuocGia,
            @Query("page") int soTrang,
            @Query("apiKey") String khoaApi
    );
}