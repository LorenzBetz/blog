package eu.lorenzroman.blog.repository.customsave;

public interface CustomSave<T> {
    <S extends T> S save(S entity);
}
