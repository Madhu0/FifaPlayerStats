package models;

import io.ebean.Model;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@MappedSuperclass
public class BaseModel extends Model {
    @Getter
    @Setter
    @Id
    @ApiModelProperty(readOnly = true, hidden = true)
    private Long id;

    @Getter
    @Setter
    @WhenCreated
    @ApiModelProperty(readOnly = true, hidden = true)
    private Instant createdAt;

    @Getter
    @Setter
    @WhenModified
    @ApiModelProperty(readOnly = true, hidden = true)
    private Instant modifiedAt;
}
