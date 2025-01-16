import com.prueba.franquicia.application.BranchUseCase;
import com.prueba.franquicia.application.FranchiseUseCase;
import com.prueba.franquicia.application.ProductUseCase;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ProductUseCase productBean() { return new ProductUseCase(); }

    @Bean
    public BranchUseCase branchBean() { return new BranchUseCase(); }

    @Bean
    public FranchiseUseCase franchiseBean() { return new FranchiseUseCase(); }
}
