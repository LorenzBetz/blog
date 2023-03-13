package eu.lorenzroman.blog.repository.customsave;

import eu.lorenzroman.blog.domain.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
class CustomSaveImpl<T extends BaseEntity> implements CustomSave<T> {
    MongoTemplate mongoTemplate;

    @Autowired
    public CustomSaveImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public <S extends T> S save(S entity) {

        if(entity.getId() != null && mongoTemplate.findById(entity.getId(), entity.getClass()) != null) {
            entity.setUpdated();
        } else {
            entity.setCreated();
        }


        return mongoTemplate.save(entity);
    }
}