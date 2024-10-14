package space.sotis.starmaa.web.models;

import cn.dev33.satoken.util.SaResult;
import lombok.Data;

/**
 * @author x1ngyu
 * @since 2024/10/11
 * <p>
 * 服务层的响应对象模型
 * <p>
 * 用于统一服务层的响应格式：<br>
 * 成功时使用 {@link ServiceResponse#success(T)}<br>
 * 失败时使用 {@link ServiceResponse#failure(String)}<br>
 */
@Data
public class ServiceResponse<T> {
    private boolean success;
    private T payload;
    private String errorMessage;

    public ServiceResponse(boolean success, T payload, String errorMessage) {
        this.success = success;
        this.payload = payload;
        this.errorMessage = errorMessage;
    }

    public ServiceResponse() {
        this.success = false;
        this.payload = null;
        this.errorMessage = null;
    }

    /**
     * 成功时的响应
     *
     * @param payload 需要返回的数据（若有）
     * @param <T>     泛型
     * @return ServiceResponse
     */
    public static <T> ServiceResponse<T> success(T payload) {
        return new ServiceResponse<>(true, payload, null);
    }

    /**
     * 失败时的响应
     *
     * @param errorMessage 错误信息
     * @param <T>          泛型
     * @return ServiceResponse
     */
    public static <T> ServiceResponse<T> failure(String errorMessage) {
        return new ServiceResponse<>(false, null, errorMessage);
    }

    /**
     * 包含SaResult时失败的响应重载
     *
     * @param errorMessage 错误信息
     * @param saResult     Sa响应结果 错误信息
     * @return ServiceResponse
     */
    public static ServiceResponse<SaResult> failure(String errorMessage, SaResult saResult) {
        return new ServiceResponse<>(false, saResult, errorMessage);
    }
}
