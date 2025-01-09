package com.capgemini.bookstore_backend.Mapper;

import com.capgemini.bookstore_backend.dto.CartDto;
import com.capgemini.bookstore_backend.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper is from MapStruct
 * This will map between entities CartDTO to Cart entity
 */

@Mapper(componentModel = "spring")
public interface CartMapper {
    /**
     * call to the getMapper method of the Mappers class
     * it's a method call that returns an instance of the BookMapper interface
     */
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    /**
     * maps Cart entity to BookDto
     * @param cart
     * @return CartDto
     */
    CartDto mapCartToCartDto(Cart cart);

    /**
     * maps CartDto to Book entity
     * @param cartDto
     * @return Cart entity
     */
    Cart mapCartDtoToCart(CartDto cartDto);
}
