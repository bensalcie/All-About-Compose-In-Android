package bensalcie.samples.allaboutcompose_android.domain.model.util

//interface EntityMapper <Entity,DomainModel>{
//}

interface EntityMapper <T,DomainModel>{
    fun mapToDomainModel(model: T):DomainModel
    fun mapFromDomainModel(domainModel: DomainModel):T
    fun toDomainModelList(initial:List<T>):List<DomainModel>
    fun fromDomainModelList(initial:List<DomainModel>):List<T>

}