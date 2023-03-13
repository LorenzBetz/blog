package eu.lorenzroman.blog.domain.mapper;

import eu.lorenzroman.blog.domain.dto.AuthorDTO;
import eu.lorenzroman.blog.domain.entity.Author;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAuthor(Author authorIn, @MappingTarget Author authorOut);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AuthorDTO toAuthorDTO(Author author);
}

