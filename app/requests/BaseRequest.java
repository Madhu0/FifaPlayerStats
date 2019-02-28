package requests;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseRequest {
    @ApiModelProperty(readOnly = true, hidden = true)
    private String requestId;
}
