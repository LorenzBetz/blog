package eu.lorenzroman.blog.domain.mapper;

import eu.lorenzroman.blog.domain.dto.CommentDTO;
import eu.lorenzroman.blog.domain.entity.Comment;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring")
public interface CommentMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateComment(Comment commentIn, @MappingTarget Comment commentOut);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CommentDTO toCommentDTO(Comment comment);
}


