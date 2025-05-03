package bg.tusofia.fcst.ksi.practikum.fds.globals.configurations;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Allergen;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Category;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address.Country;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address.PopulationCentre;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address.Region;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.allergen.AllergenJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.category.CategoryJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.country.CountryJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.population_centre.PopulationCentreJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.region.RegionJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Configuration
public class Seeder {

    public static List<String> readLinesFromFile(String filePath) throws IOException {
        return Files.readAllLines(Path.of(filePath));
    }

    @Bean
    CommandLineRunner seedAllergens(AllergenJpaRepository repository) {
        try {
            List<Allergen> allergens = readLinesFromFile("seed_data/allergens.txt").stream().map(Allergen::new).toList();

            return args -> {
                if (repository.count() != allergens.size()) {
                    repository.deleteAll();
                    repository.saveAll(allergens);

                    System.out.println("Saved all allergens");

                } else {
                    System.out.println("Allergen data already exists. Skipping seeding.");
                }
            };
        } catch (Exception e) {
            return args -> {
                System.out.println("Unexpected Error during seeding");
            };
        }
    }

    @Bean
    CommandLineRunner seedAddressData(CountryJpaRepository countryJpaRepository, RegionJpaRepository regionJpaRepository, PopulationCentreJpaRepository populationCentreJpaRepository) {
        try {
            List<Country> countries = readLinesFromFile("seed_data/countries.txt").stream().map(l -> l.split(",")).map(l -> new Country(l[1], l[0])).toList();
            List<Region> regions = readLinesFromFile("seed_data/regions.txt").stream().map(l -> new Region(countries.getFirst(), l)).toList();
            List<PopulationCentre> populationCentres = readLinesFromFile("seed_data/population_centres.csv").stream().map(l -> l.split(",")).map(l -> new PopulationCentre(regions.stream().filter(r -> r.getName().equals(l[1])).findFirst().orElseThrow(() -> new RuntimeException(String.format("Cannot find region %s", l[1]))), l[0])).toList();

            return args -> {
                if ((countryJpaRepository.count() != countries.size()) || (regionJpaRepository.count() != regions.size()) || (populationCentreJpaRepository.count() != populationCentres.size())) {
                    countryJpaRepository.deleteAll();
                    regionJpaRepository.deleteAll();
                    populationCentreJpaRepository.deleteAll();

                    countryJpaRepository.saveAll(countries);
                    regionJpaRepository.saveAll(regions);
                    populationCentreJpaRepository.saveAll(populationCentres);

                    System.out.println("Saved all population centres");

                } else {
                    System.out.println("Population centre data already exists. Skipping seeding.");
                }
            };
        } catch (Exception e) {
            return args -> {
                System.out.println("Unexpected Error during seeding");
            };
        }
    }

    @Bean
    CommandLineRunner seedCategories(CategoryJpaRepository categoryJpaRepository) {
        try {
            List<Category> categories = readLinesFromFile("seed_data/categories.txt").stream().map(Category::new).toList();

            return args -> {
                if (categoryJpaRepository.count() != categories.size()) {
                    categoryJpaRepository.deleteAll();
                    categoryJpaRepository.saveAll(categories);

                    System.out.println("Saved all categories");

                } else {
                    System.out.println("Categories data already exists. Skipping seeding.");
                }
            };
        } catch (Exception e) {
            return args -> {
                System.out.println("Unexpected Error during seeding");
            };
        }
    }
}