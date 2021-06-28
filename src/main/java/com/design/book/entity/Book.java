package com.design.book.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ezuy
 * @since 2021-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Book对象")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "book_id", type = IdType.AUTO)
    private Long bookId;

    @ApiModelProperty(value = "图书名称")
    private String bookName;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "发行日期")
    private String publicationDate;

    @ApiModelProperty(value = "价格")
    private String price;

    @ApiModelProperty(value = "ISBN")
    private String isbn;

    @ApiModelProperty(value = "图书名称")
    private String categoryName;

    @ApiModelProperty(value = "是否发布到云端的标志")
    private Boolean flag1;

    @ApiModelProperty(value = "是否同步到本地的标志")
    private Boolean flag2;

    @ApiModelProperty(value = "逻辑删除(0:未删除 1:已删除)")
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;
}
