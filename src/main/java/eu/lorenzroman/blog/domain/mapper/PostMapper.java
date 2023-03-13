package eu.lorenzroman.blog.domain.mapper;

import eu.lorenzroman.blog.domain.dto.PostDTO;
import eu.lorenzroman.blog.domain.entity.Post;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring")
public interface PostMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePost(Post postIn, @MappingTarget Post postOut);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PostDTO toPostDTO(Post comment);
}
