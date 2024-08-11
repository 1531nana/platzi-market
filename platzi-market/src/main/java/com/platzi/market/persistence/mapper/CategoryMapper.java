package com.platzi.market.persistence.mapper;

import com.platzi.market.domain.Category;
import com.platzi.market.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring") // Mapeador
public interface CategoryMapper {
    @Mappings({
                    //source Fuente         target = Destino
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active"),
    })
    Category toCategory(Categoria categoria);

    // Anotación que indica que la conversión que vamos a realizar será la inversa del mapping de arriba
    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true) //Ignorar un campo que no se está implementando en ambas clases
    Categoria toCategoria(Category category);
}
