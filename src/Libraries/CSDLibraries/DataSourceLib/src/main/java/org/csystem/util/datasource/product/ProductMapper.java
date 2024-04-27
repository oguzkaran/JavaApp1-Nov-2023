package org.csystem.util.datasource.product;

import org.csystem.util.datasource.product.dto.ProductNameStockDTO;

public class ProductMapper {
    //...
    public ProductNameStockDTO toProductStockDTO(ProductInfo productInfo)
    {
        return new ProductNameStockDTO(productInfo.getName(), productInfo.getStock());
    }
}
