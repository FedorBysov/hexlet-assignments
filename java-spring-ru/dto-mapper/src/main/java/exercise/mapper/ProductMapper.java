package exercise.mapper;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import org.hibernate.mapping.List;
import org.mapstruct.*;

// BEGIN
@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public abstract class ProductMapper {

    @Mapping(source = "title", target = "name")
    @Mapping(source = "price", target = "cost")
    @Mapping(source = "vendorCode", target = "barcode")
    public abstract Product map(ProductCreateDTO dto);

    @Mapping( target= "title", source = "name")
    @Mapping(target = "price", source = "cost")
    @Mapping(target = "vendorCode", source = "barcode")
    public abstract ProductDTO map(Product entity);

    @Mapping(source = "price", target = "cost")
    public abstract void update(ProductUpdateDTO dto, @MappingTarget Product entity);


}
// END
