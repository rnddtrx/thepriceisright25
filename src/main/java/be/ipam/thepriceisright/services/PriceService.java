package be.ipam.thepriceisright.services;

import be.ipam.thepriceisright.dto.PriceDto;
import be.ipam.thepriceisright.exceptions.ResourceNotFoundException;
import be.ipam.thepriceisright.mappers.PriceMapper;
import be.ipam.thepriceisright.models.Price;
import be.ipam.thepriceisright.models.Product;
import be.ipam.thepriceisright.models.Shop;
import be.ipam.thepriceisright.repositories.PriceRepository;
import be.ipam.thepriceisright.repositories.ProductRepository;
import be.ipam.thepriceisright.repositories.ShopRepository;
import org.springframework.stereotype.Service;

@Service
public class PriceService {

    private final PriceRepository priceRepository;
    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;

    private final PriceMapper priceMapper;

    public PriceService(PriceRepository priceRepository,
                        ProductRepository productRepository,
                        ShopRepository shopRepository,
                        PriceMapper priceMapper) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
        this.productRepository = productRepository;
        this.shopRepository = shopRepository;
    }

    //Add a price to a product
    public PriceDto addPrice(Long productId, Long shopId, PriceDto priceDto) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new ResourceNotFoundException("Shop not found with id: " + productId));

        Price price = priceMapper.toEntity(priceDto);

        price.setProduct(product);
        price.setShop(shop);

        Price savedPrice = priceRepository.save(price);

        return priceMapper.toDto(savedPrice);
    }

    //Delete price by id
    public void deletePrice(Long priceId) {
        if (!priceRepository.existsById(priceId)) {
            throw new ResourceNotFoundException("Price not found with id: " + priceId);
        }
        priceRepository.deleteById(priceId);
    }
}
