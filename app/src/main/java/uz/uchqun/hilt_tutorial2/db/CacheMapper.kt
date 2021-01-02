package uz.uchqun.hilt_tutorial2.db

import uz.uchqun.hilt_tutorial2.model.Blog
import uz.uchqun.hilt_tutorial2.utils.EntityMapper
import javax.inject.Inject

class CacheMapper @Inject constructor() : EntityMapper<BlogCacheEntity, Blog> {
    override fun mapFromEntity(entity: BlogCacheEntity): Blog {
        return Blog(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.category
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogCacheEntity {
        return BlogCacheEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image = domainModel.image,
            category = domainModel.category
        )
    }


    fun mapFromEntityList(entities: List<BlogCacheEntity>): List<Blog> {
        return entities.map { mapFromEntity(it) }
    }
}