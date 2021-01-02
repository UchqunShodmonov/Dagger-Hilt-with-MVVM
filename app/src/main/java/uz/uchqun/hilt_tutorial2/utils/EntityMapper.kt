package uz.uchqun.hilt_tutorial2.utils

interface EntityMapper <Entity,DomainModel>{


    fun mapFromEntity(entity: Entity):DomainModel


    fun mapToEntity(domainModel: DomainModel):Entity

}