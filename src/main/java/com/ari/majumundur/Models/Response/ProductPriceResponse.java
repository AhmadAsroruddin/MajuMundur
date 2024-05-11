package com.ari.majumundur.Models.Response;

import com.ari.majumundur.Models.Entities.Merchant;
import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductPriceResponse {
    private String priceId;
    private String price;
    private String stock;
    private Merchant merchant;
}
