package be.ipam.thepriceisright.services;

import be.ipam.thepriceisright.dto.ShopDto;
import be.ipam.thepriceisright.exceptions.BadRequestException;
import be.ipam.thepriceisright.exceptions.ConflictException;
import be.ipam.thepriceisright.mappers.ShopMapper;
import be.ipam.thepriceisright.models.Shop;
import be.ipam.thepriceisright.repositories.ProductRepository;
import be.ipam.thepriceisright.repositories.ShopRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private final ShopRepository shopRepository;
    private final ShopMapper shopMapper;

    public ShopService(ShopRepository shopRepository,
                       ShopMapper shopMapper,
                       ProductRepository productRepository) {
        this.shopRepository = shopRepository;
        this.shopMapper = shopMapper;
    }

    //Create shop
    public ShopDto createShop(ShopDto shopDto) {
        if(shopRepository.existsByName(shopDto.getName())){
            throw new RuntimeException("Shop already exists with name: " + shopDto.getName());
        }

        Shop shop = shopMapper.toEntity(shopDto);
        try {
            return shopMapper.toDto(shopRepository.save(shop));
        }
        catch (DataIntegrityViolationException ex) {
            Throwable root = ex.getMostSpecificCause();
            String message = root.getMessage().toLowerCase();

            // Violation de contrainte unique : 409 Conflict
            if (message.contains("unique") || message.contains("duplicate") || message.contains("constraint")) {
                throw new ConflictException("A shop with this name already exists: " + shopDto.getName());
            }

            // Autres erreurs d'intégrité : 400 Bad Request
            throw new BadRequestException("Invalid shop data: " + message);
        }
    }

    // List shops paginated
    public Page<ShopDto> listShops(Pageable pageable) {
        return shopRepository.findAll(pageable).map(shopMapper::toDto);
    }

    //Find shop by id
    public ShopDto findShopById(Long shopId) {
        return shopRepository.findById(shopId).map(shopMapper::toDto)
            .orElseThrow(() -> new RuntimeException("Shop not found with id: " + shopId));
    }

    //Delete shop by id
    public void deleteShopById(Long shopId) {
        if(!shopRepository.existsById(shopId)){
            throw new RuntimeException("Shop not found with id: " + shopId);
        }
        shopRepository.deleteById(shopId);
    }
}
