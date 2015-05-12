
package com.paypal.hybris.soap.gen;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BusinessSubCategoryType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BusinessSubCategoryType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="SubCategory-Unspecified"/>
 *     &lt;enumeration value="ANTIQUES-General"/>
 *     &lt;enumeration value="ANTIQUES-Antiquities"/>
 *     &lt;enumeration value="ANTIQUES-Decorative"/>
 *     &lt;enumeration value="ANTIQUES-Books-Manuscripts"/>
 *     &lt;enumeration value="ANTIQUES-Furniture"/>
 *     &lt;enumeration value="ANTIQUES-Glass"/>
 *     &lt;enumeration value="ANTIQUES-RugsCarpets"/>
 *     &lt;enumeration value="ANTIQUES-Pottery"/>
 *     &lt;enumeration value="ANTIQUES-Cultural"/>
 *     &lt;enumeration value="ANTIQUES-Artifacts-Grave-related-and-Native-American-Crafts"/>
 *     &lt;enumeration value="ARTSANDCRAFTS-General"/>
 *     &lt;enumeration value="ARTSANDCRAFTS-Art-Dealer-and-Galleries"/>
 *     &lt;enumeration value="ARTSANDCRAFTS-Prints"/>
 *     &lt;enumeration value="ARTSANDCRAFTS-Painting"/>
 *     &lt;enumeration value="ARTSANDCRAFTS-Photography"/>
 *     &lt;enumeration value="ARTSANDCRAFTS-Reproductions"/>
 *     &lt;enumeration value="ARTSANDCRAFTS-Sculptures"/>
 *     &lt;enumeration value="ARTSANDCRAFTS-Woodworking"/>
 *     &lt;enumeration value="ARTSANDCRAFTS-Art-and-Craft-Supplies"/>
 *     &lt;enumeration value="ARTSANDCRAFTS-Fabrics-and-Sewing"/>
 *     &lt;enumeration value="ARTSANDCRAFTS-Quilting"/>
 *     &lt;enumeration value="ARTSANDCRAFTS-Scrapbooking"/>
 *     &lt;enumeration value="AUTOMOTIVE-General"/>
 *     &lt;enumeration value="AUTOMOTIVE-Autos"/>
 *     &lt;enumeration value="AUTOMOTIVE-Aviation"/>
 *     &lt;enumeration value="AUTOMOTIVE-Motorcycles"/>
 *     &lt;enumeration value="AUTOMOTIVE-Parts-and-Supplies"/>
 *     &lt;enumeration value="AUTOMOTIVE-Services"/>
 *     &lt;enumeration value="AUTOMOTIVE-Vintage-and-Collectible-Vehicles"/>
 *     &lt;enumeration value="BEAUTY-General"/>
 *     &lt;enumeration value="BEAUTY-Body-Care-Personal-Hygiene"/>
 *     &lt;enumeration value="BEAUTY-Fragrances-and-Perfumes"/>
 *     &lt;enumeration value="BEAUTY-Makeup"/>
 *     &lt;enumeration value="BOOKS-General"/>
 *     &lt;enumeration value="BOOKS-Audio-Books"/>
 *     &lt;enumeration value="BOOKS-Children-Books"/>
 *     &lt;enumeration value="BOOKS-Computer-Books"/>
 *     &lt;enumeration value="BOOKS-Educational-and-Textbooks"/>
 *     &lt;enumeration value="BOOKS-Magazines"/>
 *     &lt;enumeration value="BOOKS-Fiction-and-Literature"/>
 *     &lt;enumeration value="BOOKS-NonFiction"/>
 *     &lt;enumeration value="BOOKS-Vintage-and-Collectibles"/>
 *     &lt;enumeration value="BUSINESS-General"/>
 *     &lt;enumeration value="BUSINESS-Agricultural"/>
 *     &lt;enumeration value="BUSINESS-Construction"/>
 *     &lt;enumeration value="BUSINESS-Educational"/>
 *     &lt;enumeration value="BUSINESS-Industrial"/>
 *     &lt;enumeration value="BUSINESS-Office-Supplies-and-Equipment"/>
 *     &lt;enumeration value="BUSINESS-GeneralServices"/>
 *     &lt;enumeration value="BUSINESS-Advertising"/>
 *     &lt;enumeration value="BUSINESS-Employment"/>
 *     &lt;enumeration value="BUSINESS-Marketing"/>
 *     &lt;enumeration value="BUSINESS-Meeting-Planners"/>
 *     &lt;enumeration value="BUSINESS-Messaging-and-Paging-Services"/>
 *     &lt;enumeration value="BUSINESS-Seminars"/>
 *     &lt;enumeration value="BUSINESS-Publishing"/>
 *     &lt;enumeration value="BUSINESS-Shipping-and-Packaging"/>
 *     &lt;enumeration value="BUSINESS-Wholesale"/>
 *     &lt;enumeration value="BUSINESS-Industrial-Solvents"/>
 *     &lt;enumeration value="CAMERASANDPHOTOGRAPHY-General"/>
 *     &lt;enumeration value="CAMERASANDPHOTOGRAPHY-Accessories"/>
 *     &lt;enumeration value="CAMERASANDPHOTOGRAPHY-Cameras"/>
 *     &lt;enumeration value="CAMERASANDPHOTOGRAPHY-Video-Equipment"/>
 *     &lt;enumeration value="CAMERASANDPHOTOGRAPHY-Film"/>
 *     &lt;enumeration value="CAMERASANDPHOTOGRAPHY-Supplies"/>
 *     &lt;enumeration value="CLOTHING-Accessories"/>
 *     &lt;enumeration value="CLOTHING-Babies-Clothing-and-Supplies"/>
 *     &lt;enumeration value="CLOTHING-Childrens-Clothing"/>
 *     &lt;enumeration value="CLOTHING-Mens-Clothing"/>
 *     &lt;enumeration value="CLOTHING-Shoes"/>
 *     &lt;enumeration value="CLOTHING-Wedding-Clothing"/>
 *     &lt;enumeration value="CLOTHING-Womens-Clothing"/>
 *     &lt;enumeration value="CLOTHING-General"/>
 *     &lt;enumeration value="CLOTHING-Jewelry"/>
 *     &lt;enumeration value="CLOTHING-Watches-and-Clocks"/>
 *     &lt;enumeration value="CLOTHING-Rings"/>
 *     &lt;enumeration value="COLLECTIBLES-General"/>
 *     &lt;enumeration value="COLLECTIBLES-Advertising"/>
 *     &lt;enumeration value="COLLECTIBLES-Animals"/>
 *     &lt;enumeration value="COLLECTIBLES-Animation"/>
 *     &lt;enumeration value="COLLECTIBLES-Coin-Operated-Banks-and-Casinos"/>
 *     &lt;enumeration value="COLLECTIBLES-Coins-and-Paper-Money"/>
 *     &lt;enumeration value="COLLECTIBLES-Comics"/>
 *     &lt;enumeration value="COLLECTIBLES-Decorative"/>
 *     &lt;enumeration value="COLLECTIBLES-Disneyana"/>
 *     &lt;enumeration value="COLLECTIBLES-Holiday"/>
 *     &lt;enumeration value="COLLECTIBLES-Knives-and-Swords"/>
 *     &lt;enumeration value="COLLECTIBLES-Militaria"/>
 *     &lt;enumeration value="COLLECTIBLES-Postcards-and-Paper"/>
 *     &lt;enumeration value="COLLECTIBLES-Stamps"/>
 *     &lt;enumeration value="COMPUTERHARDWAREANDSOFTWARE-General"/>
 *     &lt;enumeration value="COMPUTERHARDWAREANDSOFTWARE-Desktop-PCs"/>
 *     &lt;enumeration value="COMPUTERHARDWAREANDSOFTWARE-Monitors"/>
 *     &lt;enumeration value="COMPUTERHARDWAREANDSOFTWARE-Hardware"/>
 *     &lt;enumeration value="COMPUTERHARDWAREANDSOFTWARE-Peripherals"/>
 *     &lt;enumeration value="COMPUTERHARDWAREANDSOFTWARE-Laptops-Notebooks-PDAs"/>
 *     &lt;enumeration value="COMPUTERHARDWAREANDSOFTWARE-Networking-Equipment"/>
 *     &lt;enumeration value="COMPUTERHARDWAREANDSOFTWARE-Parts-and-Accessories"/>
 *     &lt;enumeration value="COMPUTERHARDWAREANDSOFTWARE-GeneralSoftware"/>
 *     &lt;enumeration value="COMPUTERHARDWAREANDSOFTWARE-Oem-Software"/>
 *     &lt;enumeration value="COMPUTERHARDWAREANDSOFTWARE-Academic-Software"/>
 *     &lt;enumeration value="COMPUTERHARDWAREANDSOFTWARE-Beta-Software"/>
 *     &lt;enumeration value="COMPUTERHARDWAREANDSOFTWARE-Game-Software"/>
 *     &lt;enumeration value="COMPUTERHARDWAREANDSOFTWARE-Data-Processing-Svc"/>
 *     &lt;enumeration value="CULTUREANDRELIGION-General"/>
 *     &lt;enumeration value="CULTUREANDRELIGION-Christianity"/>
 *     &lt;enumeration value="CULTUREANDRELIGION-Metaphysical"/>
 *     &lt;enumeration value="CULTUREANDRELIGION-New-Age"/>
 *     &lt;enumeration value="CULTUREANDRELIGION-Organizations"/>
 *     &lt;enumeration value="CULTUREANDRELIGION-Other-Faiths"/>
 *     &lt;enumeration value="CULTUREANDRELIGION-Collectibles"/>
 *     &lt;enumeration value="ELECTRONICSANDTELECOM-GeneralTelecom"/>
 *     &lt;enumeration value="ELECTRONICSANDTELECOM-Cell-Phones-and-Pagers"/>
 *     &lt;enumeration value="ELECTRONICSANDTELECOM-Telephone-Cards"/>
 *     &lt;enumeration value="ELECTRONICSANDTELECOM-Telephone-Equipment"/>
 *     &lt;enumeration value="ELECTRONICSANDTELECOM-Telephone-Services"/>
 *     &lt;enumeration value="ELECTRONICSANDTELECOM-GeneralElectronics"/>
 *     &lt;enumeration value="ELECTRONICSANDTELECOM-Car-Audio-and-Electronics"/>
 *     &lt;enumeration value="ELECTRONICSANDTELECOM-Home-Electronics"/>
 *     &lt;enumeration value="ELECTRONICSANDTELECOM-Home-Audio"/>
 *     &lt;enumeration value="ELECTRONICSANDTELECOM-Gadgets-and-other-electronics"/>
 *     &lt;enumeration value="ELECTRONICSANDTELECOM-Batteries"/>
 *     &lt;enumeration value="ELECTRONICSANDTELECOM-ScannersRadios"/>
 *     &lt;enumeration value="ELECTRONICSANDTELECOM-Radar-Dectors"/>
 *     &lt;enumeration value="ELECTRONICSANDTELECOM-Radar-Jamming-Devices"/>
 *     &lt;enumeration value="ELECTRONICSANDTELECOM-Satellite-and-Cable-TV-Descramblers"/>
 *     &lt;enumeration value="ELECTRONICSANDTELECOM-Surveillance-Equipment"/>
 *     &lt;enumeration value="ENTERTAINMENT-General"/>
 *     &lt;enumeration value="ENTERTAINMENT-Movies"/>
 *     &lt;enumeration value="ENTERTAINMENT-Music"/>
 *     &lt;enumeration value="ENTERTAINMENT-Concerts"/>
 *     &lt;enumeration value="ENTERTAINMENT-Theater"/>
 *     &lt;enumeration value="ENTERTAINMENT-Bootleg-Recordings"/>
 *     &lt;enumeration value="ENTERTAINMENT-Promotional-Items"/>
 *     &lt;enumeration value="ENTERTAINMENTMEMORABILIA-General"/>
 *     &lt;enumeration value="ENTERTAINMENTMEMORABILIA-Autographs"/>
 *     &lt;enumeration value="ENTERTAINMENTMEMORABILIA-Limited-Editions"/>
 *     &lt;enumeration value="ENTERTAINMENTMEMORABILIA-Movie"/>
 *     &lt;enumeration value="ENTERTAINMENTMEMORABILIA-Music"/>
 *     &lt;enumeration value="ENTERTAINMENTMEMORABILIA-Novelties"/>
 *     &lt;enumeration value="ENTERTAINMENTMEMORABILIA-Photos"/>
 *     &lt;enumeration value="ENTERTAINMENTMEMORABILIA-Posters"/>
 *     &lt;enumeration value="ENTERTAINMENTMEMORABILIA-Sports-and-Fan-Shop"/>
 *     &lt;enumeration value="ENTERTAINMENTMEMORABILIA-Science-Fiction"/>
 *     &lt;enumeration value="FOODDRINKANDNUTRITION-General"/>
 *     &lt;enumeration value="FOODDRINKANDNUTRITION-Coffee-and-Tea"/>
 *     &lt;enumeration value="FOODDRINKANDNUTRITION-Food-Products"/>
 *     &lt;enumeration value="FOODDRINKANDNUTRITION-Gourmet-Items"/>
 *     &lt;enumeration value="FOODDRINKANDNUTRITION-Health-and-Nutrition"/>
 *     &lt;enumeration value="FOODDRINKANDNUTRITION-Services"/>
 *     &lt;enumeration value="FOODDRINKANDNUTRITION-Vitamins-and-Supplements"/>
 *     &lt;enumeration value="FOODDRINKANDNUTRITION-Weight-Management-and-Health-Products"/>
 *     &lt;enumeration value="FOODDRINKANDNUTRITION-Restaurant"/>
 *     &lt;enumeration value="FOODDRINKANDNUTRITION-Tobacco-and-Cigars"/>
 *     &lt;enumeration value="FOODDRINKANDNUTRITION-Alcoholic-Beverages"/>
 *     &lt;enumeration value="GIFTSANDFLOWERS-General"/>
 *     &lt;enumeration value="GIFTSANDFLOWERS-Flowers"/>
 *     &lt;enumeration value="GIFTSANDFLOWERS-Greeting-Cards"/>
 *     &lt;enumeration value="GIFTSANDFLOWERS-Humorous-Gifts-and-Novelties"/>
 *     &lt;enumeration value="GIFTSANDFLOWERS-Personalized-Gifts"/>
 *     &lt;enumeration value="GIFTSANDFLOWERS-Products"/>
 *     &lt;enumeration value="GIFTSANDFLOWERS-Services"/>
 *     &lt;enumeration value="HOBBIESTOYSANDGAMES-General"/>
 *     &lt;enumeration value="HOBBIESTOYSANDGAMES-Action-Figures"/>
 *     &lt;enumeration value="HOBBIESTOYSANDGAMES-Bean-Babies"/>
 *     &lt;enumeration value="HOBBIESTOYSANDGAMES-Barbies"/>
 *     &lt;enumeration value="HOBBIESTOYSANDGAMES-Bears"/>
 *     &lt;enumeration value="HOBBIESTOYSANDGAMES-Dolls"/>
 *     &lt;enumeration value="HOBBIESTOYSANDGAMES-Games"/>
 *     &lt;enumeration value="HOBBIESTOYSANDGAMES-Model-Kits"/>
 *     &lt;enumeration value="HOBBIESTOYSANDGAMES-Diecast-Toys-Vehicles"/>
 *     &lt;enumeration value="HOBBIESTOYSANDGAMES-Video-Games-and-Systems"/>
 *     &lt;enumeration value="HOBBIESTOYSANDGAMES-Vintage-and-Antique-Toys"/>
 *     &lt;enumeration value="HOBBIESTOYSANDGAMES-BackupUnreleased-Games"/>
 *     &lt;enumeration value="HOBBIESTOYSANDGAMES-Game-copying-hardwaresoftware"/>
 *     &lt;enumeration value="HOBBIESTOYSANDGAMES-Mod-Chips"/>
 *     &lt;enumeration value="HOMEANDGARDEN-General"/>
 *     &lt;enumeration value="HOMEANDGARDEN-Appliances"/>
 *     &lt;enumeration value="HOMEANDGARDEN-Bed-and-Bath"/>
 *     &lt;enumeration value="HOMEANDGARDEN-Furnishing-and-Decorating"/>
 *     &lt;enumeration value="HOMEANDGARDEN-Garden-Supplies"/>
 *     &lt;enumeration value="HOMEANDGARDEN-Hardware-and-Tools"/>
 *     &lt;enumeration value="HOMEANDGARDEN-Household-Goods"/>
 *     &lt;enumeration value="HOMEANDGARDEN-Kitchenware"/>
 *     &lt;enumeration value="HOMEANDGARDEN-Rugs-and-Carpets"/>
 *     &lt;enumeration value="HOMEANDGARDEN-Security-and-Home-Defense"/>
 *     &lt;enumeration value="HOMEANDGARDEN-Plants-and-Seeds"/>
 *     &lt;enumeration value="INTERNETANDNETWORKSERVICES-General"/>
 *     &lt;enumeration value="INTERNETANDNETWORKSERVICES-Bulletin-board"/>
 *     &lt;enumeration value="INTERNETANDNETWORKSERVICES-online-services"/>
 *     &lt;enumeration value="INTERNETANDNETWORKSERVICES-Auction-management-tools"/>
 *     &lt;enumeration value="INTERNETANDNETWORKSERVICES-ecommerce-development"/>
 *     &lt;enumeration value="INTERNETANDNETWORKSERVICES-training-services"/>
 *     &lt;enumeration value="INTERNETANDNETWORKSERVICES-Online-Malls"/>
 *     &lt;enumeration value="INTERNETANDNETWORKSERVICES-Web-hosting-and-design"/>
 *     &lt;enumeration value="MEDIAANDENTERTAINMENT-General"/>
 *     &lt;enumeration value="MEDIAANDENTERTAINMENT-Concerts"/>
 *     &lt;enumeration value="MEDIAANDENTERTAINMENT-Theater"/>
 *     &lt;enumeration value="MEDICALANDPHARMACEUTICAL-General"/>
 *     &lt;enumeration value="MEDICALANDPHARMACEUTICAL-Medical"/>
 *     &lt;enumeration value="MEDICALANDPHARMACEUTICAL-Dental"/>
 *     &lt;enumeration value="MEDICALANDPHARMACEUTICAL-Opthamalic"/>
 *     &lt;enumeration value="MEDICALANDPHARMACEUTICAL-Prescription-Drugs"/>
 *     &lt;enumeration value="MEDICALANDPHARMACEUTICAL-Devices"/>
 *     &lt;enumeration value="MONEYSERVICEBUSINESSES-General"/>
 *     &lt;enumeration value="MONEYSERVICEBUSINESSES-Remittance"/>
 *     &lt;enumeration value="MONEYSERVICEBUSINESSES-Wire-Transfer"/>
 *     &lt;enumeration value="MONEYSERVICEBUSINESSES-Money-Orders"/>
 *     &lt;enumeration value="MONEYSERVICEBUSINESSES-Electronic-Cash"/>
 *     &lt;enumeration value="MONEYSERVICEBUSINESSES-Currency-DealerExchange"/>
 *     &lt;enumeration value="MONEYSERVICEBUSINESSES-Check-Cashier"/>
 *     &lt;enumeration value="MONEYSERVICEBUSINESSES-Travelers-Checks"/>
 *     &lt;enumeration value="MONEYSERVICEBUSINESSES-Stored-Value-Cards"/>
 *     &lt;enumeration value="NONPROFITPOLITICALANDRELIGION-General"/>
 *     &lt;enumeration value="NONPROFITPOLITICALANDRELIGION-Charities"/>
 *     &lt;enumeration value="NONPROFITPOLITICALANDRELIGION-Political"/>
 *     &lt;enumeration value="NONPROFITPOLITICALANDRELIGION-Religious"/>
 *     &lt;enumeration value="PETSANDANIMALS-General"/>
 *     &lt;enumeration value="PETSANDANIMALS-Supplies-and-Toys"/>
 *     &lt;enumeration value="PETSANDANIMALS-Wildlife-Products"/>
 *     &lt;enumeration value="REALESTATE-General"/>
 *     &lt;enumeration value="REALESTATE-Commercial"/>
 *     &lt;enumeration value="REALESTATE-Residential"/>
 *     &lt;enumeration value="REALESTATE-Time-Shares"/>
 *     &lt;enumeration value="SERVICES-GeneralGovernment"/>
 *     &lt;enumeration value="SERVICES-Legal"/>
 *     &lt;enumeration value="SERVICES-Medical"/>
 *     &lt;enumeration value="SERVICES-Dental"/>
 *     &lt;enumeration value="SERVICES-Vision"/>
 *     &lt;enumeration value="SERVICES-General"/>
 *     &lt;enumeration value="SERVICES-Child-Care-Services"/>
 *     &lt;enumeration value="SERVICES-Consulting"/>
 *     &lt;enumeration value="SERVICES-ImportingExporting"/>
 *     &lt;enumeration value="SERVICES-InsuranceDirect"/>
 *     &lt;enumeration value="SERVICES-Financial-Services"/>
 *     &lt;enumeration value="SERVICES-Graphic-and-Commercial-Design"/>
 *     &lt;enumeration value="SERVICES-Landscaping"/>
 *     &lt;enumeration value="SERVICES-Locksmith"/>
 *     &lt;enumeration value="SERVICES-Online-Dating"/>
 *     &lt;enumeration value="SERVICES-Event-and-Wedding-Planning"/>
 *     &lt;enumeration value="SERVICES-Schools-and-Colleges"/>
 *     &lt;enumeration value="SERVICES-Entertainment"/>
 *     &lt;enumeration value="SERVICES-Aggregators"/>
 *     &lt;enumeration value="SPORTSANDRECREATION-General"/>
 *     &lt;enumeration value="SPORTSANDRECREATION-Bicycles-and-Accessories"/>
 *     &lt;enumeration value="SPORTSANDRECREATION-Boating-Sailing-and-Accessories"/>
 *     &lt;enumeration value="SPORTSANDRECREATION-Camping-and-Survival"/>
 *     &lt;enumeration value="SPORTSANDRECREATION-Exercise-Equipment"/>
 *     &lt;enumeration value="SPORTSANDRECREATION-Fishing"/>
 *     &lt;enumeration value="SPORTSANDRECREATION-Golf"/>
 *     &lt;enumeration value="SPORTSANDRECREATION-Hunting"/>
 *     &lt;enumeration value="SPORTSANDRECREATION-Paintball"/>
 *     &lt;enumeration value="SPORTSANDRECREATION-Sporting-Goods"/>
 *     &lt;enumeration value="SPORTSANDRECREATION-Swimming-Pools-and-Spas"/>
 *     &lt;enumeration value="TRAVEL-General"/>
 *     &lt;enumeration value="TRAVEL-Accommodations"/>
 *     &lt;enumeration value="TRAVEL-Agencies"/>
 *     &lt;enumeration value="TRAVEL-Airlines"/>
 *     &lt;enumeration value="TRAVEL-Auto-Rentals"/>
 *     &lt;enumeration value="TRAVEL-Cruises"/>
 *     &lt;enumeration value="TRAVEL-Other-Transportation"/>
 *     &lt;enumeration value="TRAVEL-Services"/>
 *     &lt;enumeration value="TRAVEL-Supplies"/>
 *     &lt;enumeration value="TRAVEL-Tours"/>
 *     &lt;enumeration value="TRAVEL-AirlinesSpirit-Air"/>
 *     &lt;enumeration value="Other-SubCategories"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BusinessSubCategoryType")
@XmlEnum
public enum BusinessSubCategoryType {

    @XmlEnumValue("SubCategory-Unspecified")
    SUB_CATEGORY_UNSPECIFIED("SubCategory-Unspecified"),
    @XmlEnumValue("ANTIQUES-General")
    ANTIQUES_GENERAL("ANTIQUES-General"),
    @XmlEnumValue("ANTIQUES-Antiquities")
    ANTIQUES_ANTIQUITIES("ANTIQUES-Antiquities"),
    @XmlEnumValue("ANTIQUES-Decorative")
    ANTIQUES_DECORATIVE("ANTIQUES-Decorative"),
    @XmlEnumValue("ANTIQUES-Books-Manuscripts")
    ANTIQUES_BOOKS_MANUSCRIPTS("ANTIQUES-Books-Manuscripts"),
    @XmlEnumValue("ANTIQUES-Furniture")
    ANTIQUES_FURNITURE("ANTIQUES-Furniture"),
    @XmlEnumValue("ANTIQUES-Glass")
    ANTIQUES_GLASS("ANTIQUES-Glass"),
    @XmlEnumValue("ANTIQUES-RugsCarpets")
    ANTIQUES_RUGS_CARPETS("ANTIQUES-RugsCarpets"),
    @XmlEnumValue("ANTIQUES-Pottery")
    ANTIQUES_POTTERY("ANTIQUES-Pottery"),
    @XmlEnumValue("ANTIQUES-Cultural")
    ANTIQUES_CULTURAL("ANTIQUES-Cultural"),
    @XmlEnumValue("ANTIQUES-Artifacts-Grave-related-and-Native-American-Crafts")
    ANTIQUES_ARTIFACTS_GRAVE_RELATED_AND_NATIVE_AMERICAN_CRAFTS("ANTIQUES-Artifacts-Grave-related-and-Native-American-Crafts"),
    @XmlEnumValue("ARTSANDCRAFTS-General")
    ARTSANDCRAFTS_GENERAL("ARTSANDCRAFTS-General"),
    @XmlEnumValue("ARTSANDCRAFTS-Art-Dealer-and-Galleries")
    ARTSANDCRAFTS_ART_DEALER_AND_GALLERIES("ARTSANDCRAFTS-Art-Dealer-and-Galleries"),
    @XmlEnumValue("ARTSANDCRAFTS-Prints")
    ARTSANDCRAFTS_PRINTS("ARTSANDCRAFTS-Prints"),
    @XmlEnumValue("ARTSANDCRAFTS-Painting")
    ARTSANDCRAFTS_PAINTING("ARTSANDCRAFTS-Painting"),
    @XmlEnumValue("ARTSANDCRAFTS-Photography")
    ARTSANDCRAFTS_PHOTOGRAPHY("ARTSANDCRAFTS-Photography"),
    @XmlEnumValue("ARTSANDCRAFTS-Reproductions")
    ARTSANDCRAFTS_REPRODUCTIONS("ARTSANDCRAFTS-Reproductions"),
    @XmlEnumValue("ARTSANDCRAFTS-Sculptures")
    ARTSANDCRAFTS_SCULPTURES("ARTSANDCRAFTS-Sculptures"),
    @XmlEnumValue("ARTSANDCRAFTS-Woodworking")
    ARTSANDCRAFTS_WOODWORKING("ARTSANDCRAFTS-Woodworking"),
    @XmlEnumValue("ARTSANDCRAFTS-Art-and-Craft-Supplies")
    ARTSANDCRAFTS_ART_AND_CRAFT_SUPPLIES("ARTSANDCRAFTS-Art-and-Craft-Supplies"),
    @XmlEnumValue("ARTSANDCRAFTS-Fabrics-and-Sewing")
    ARTSANDCRAFTS_FABRICS_AND_SEWING("ARTSANDCRAFTS-Fabrics-and-Sewing"),
    @XmlEnumValue("ARTSANDCRAFTS-Quilting")
    ARTSANDCRAFTS_QUILTING("ARTSANDCRAFTS-Quilting"),
    @XmlEnumValue("ARTSANDCRAFTS-Scrapbooking")
    ARTSANDCRAFTS_SCRAPBOOKING("ARTSANDCRAFTS-Scrapbooking"),
    @XmlEnumValue("AUTOMOTIVE-General")
    AUTOMOTIVE_GENERAL("AUTOMOTIVE-General"),
    @XmlEnumValue("AUTOMOTIVE-Autos")
    AUTOMOTIVE_AUTOS("AUTOMOTIVE-Autos"),
    @XmlEnumValue("AUTOMOTIVE-Aviation")
    AUTOMOTIVE_AVIATION("AUTOMOTIVE-Aviation"),
    @XmlEnumValue("AUTOMOTIVE-Motorcycles")
    AUTOMOTIVE_MOTORCYCLES("AUTOMOTIVE-Motorcycles"),
    @XmlEnumValue("AUTOMOTIVE-Parts-and-Supplies")
    AUTOMOTIVE_PARTS_AND_SUPPLIES("AUTOMOTIVE-Parts-and-Supplies"),
    @XmlEnumValue("AUTOMOTIVE-Services")
    AUTOMOTIVE_SERVICES("AUTOMOTIVE-Services"),
    @XmlEnumValue("AUTOMOTIVE-Vintage-and-Collectible-Vehicles")
    AUTOMOTIVE_VINTAGE_AND_COLLECTIBLE_VEHICLES("AUTOMOTIVE-Vintage-and-Collectible-Vehicles"),
    @XmlEnumValue("BEAUTY-General")
    BEAUTY_GENERAL("BEAUTY-General"),
    @XmlEnumValue("BEAUTY-Body-Care-Personal-Hygiene")
    BEAUTY_BODY_CARE_PERSONAL_HYGIENE("BEAUTY-Body-Care-Personal-Hygiene"),
    @XmlEnumValue("BEAUTY-Fragrances-and-Perfumes")
    BEAUTY_FRAGRANCES_AND_PERFUMES("BEAUTY-Fragrances-and-Perfumes"),
    @XmlEnumValue("BEAUTY-Makeup")
    BEAUTY_MAKEUP("BEAUTY-Makeup"),
    @XmlEnumValue("BOOKS-General")
    BOOKS_GENERAL("BOOKS-General"),
    @XmlEnumValue("BOOKS-Audio-Books")
    BOOKS_AUDIO_BOOKS("BOOKS-Audio-Books"),
    @XmlEnumValue("BOOKS-Children-Books")
    BOOKS_CHILDREN_BOOKS("BOOKS-Children-Books"),
    @XmlEnumValue("BOOKS-Computer-Books")
    BOOKS_COMPUTER_BOOKS("BOOKS-Computer-Books"),
    @XmlEnumValue("BOOKS-Educational-and-Textbooks")
    BOOKS_EDUCATIONAL_AND_TEXTBOOKS("BOOKS-Educational-and-Textbooks"),
    @XmlEnumValue("BOOKS-Magazines")
    BOOKS_MAGAZINES("BOOKS-Magazines"),
    @XmlEnumValue("BOOKS-Fiction-and-Literature")
    BOOKS_FICTION_AND_LITERATURE("BOOKS-Fiction-and-Literature"),
    @XmlEnumValue("BOOKS-NonFiction")
    BOOKS_NON_FICTION("BOOKS-NonFiction"),
    @XmlEnumValue("BOOKS-Vintage-and-Collectibles")
    BOOKS_VINTAGE_AND_COLLECTIBLES("BOOKS-Vintage-and-Collectibles"),
    @XmlEnumValue("BUSINESS-General")
    BUSINESS_GENERAL("BUSINESS-General"),
    @XmlEnumValue("BUSINESS-Agricultural")
    BUSINESS_AGRICULTURAL("BUSINESS-Agricultural"),
    @XmlEnumValue("BUSINESS-Construction")
    BUSINESS_CONSTRUCTION("BUSINESS-Construction"),
    @XmlEnumValue("BUSINESS-Educational")
    BUSINESS_EDUCATIONAL("BUSINESS-Educational"),
    @XmlEnumValue("BUSINESS-Industrial")
    BUSINESS_INDUSTRIAL("BUSINESS-Industrial"),
    @XmlEnumValue("BUSINESS-Office-Supplies-and-Equipment")
    BUSINESS_OFFICE_SUPPLIES_AND_EQUIPMENT("BUSINESS-Office-Supplies-and-Equipment"),
    @XmlEnumValue("BUSINESS-GeneralServices")
    BUSINESS_GENERAL_SERVICES("BUSINESS-GeneralServices"),
    @XmlEnumValue("BUSINESS-Advertising")
    BUSINESS_ADVERTISING("BUSINESS-Advertising"),
    @XmlEnumValue("BUSINESS-Employment")
    BUSINESS_EMPLOYMENT("BUSINESS-Employment"),
    @XmlEnumValue("BUSINESS-Marketing")
    BUSINESS_MARKETING("BUSINESS-Marketing"),
    @XmlEnumValue("BUSINESS-Meeting-Planners")
    BUSINESS_MEETING_PLANNERS("BUSINESS-Meeting-Planners"),
    @XmlEnumValue("BUSINESS-Messaging-and-Paging-Services")
    BUSINESS_MESSAGING_AND_PAGING_SERVICES("BUSINESS-Messaging-and-Paging-Services"),
    @XmlEnumValue("BUSINESS-Seminars")
    BUSINESS_SEMINARS("BUSINESS-Seminars"),
    @XmlEnumValue("BUSINESS-Publishing")
    BUSINESS_PUBLISHING("BUSINESS-Publishing"),
    @XmlEnumValue("BUSINESS-Shipping-and-Packaging")
    BUSINESS_SHIPPING_AND_PACKAGING("BUSINESS-Shipping-and-Packaging"),
    @XmlEnumValue("BUSINESS-Wholesale")
    BUSINESS_WHOLESALE("BUSINESS-Wholesale"),
    @XmlEnumValue("BUSINESS-Industrial-Solvents")
    BUSINESS_INDUSTRIAL_SOLVENTS("BUSINESS-Industrial-Solvents"),
    @XmlEnumValue("CAMERASANDPHOTOGRAPHY-General")
    CAMERASANDPHOTOGRAPHY_GENERAL("CAMERASANDPHOTOGRAPHY-General"),
    @XmlEnumValue("CAMERASANDPHOTOGRAPHY-Accessories")
    CAMERASANDPHOTOGRAPHY_ACCESSORIES("CAMERASANDPHOTOGRAPHY-Accessories"),
    @XmlEnumValue("CAMERASANDPHOTOGRAPHY-Cameras")
    CAMERASANDPHOTOGRAPHY_CAMERAS("CAMERASANDPHOTOGRAPHY-Cameras"),
    @XmlEnumValue("CAMERASANDPHOTOGRAPHY-Video-Equipment")
    CAMERASANDPHOTOGRAPHY_VIDEO_EQUIPMENT("CAMERASANDPHOTOGRAPHY-Video-Equipment"),
    @XmlEnumValue("CAMERASANDPHOTOGRAPHY-Film")
    CAMERASANDPHOTOGRAPHY_FILM("CAMERASANDPHOTOGRAPHY-Film"),
    @XmlEnumValue("CAMERASANDPHOTOGRAPHY-Supplies")
    CAMERASANDPHOTOGRAPHY_SUPPLIES("CAMERASANDPHOTOGRAPHY-Supplies"),
    @XmlEnumValue("CLOTHING-Accessories")
    CLOTHING_ACCESSORIES("CLOTHING-Accessories"),
    @XmlEnumValue("CLOTHING-Babies-Clothing-and-Supplies")
    CLOTHING_BABIES_CLOTHING_AND_SUPPLIES("CLOTHING-Babies-Clothing-and-Supplies"),
    @XmlEnumValue("CLOTHING-Childrens-Clothing")
    CLOTHING_CHILDRENS_CLOTHING("CLOTHING-Childrens-Clothing"),
    @XmlEnumValue("CLOTHING-Mens-Clothing")
    CLOTHING_MENS_CLOTHING("CLOTHING-Mens-Clothing"),
    @XmlEnumValue("CLOTHING-Shoes")
    CLOTHING_SHOES("CLOTHING-Shoes"),
    @XmlEnumValue("CLOTHING-Wedding-Clothing")
    CLOTHING_WEDDING_CLOTHING("CLOTHING-Wedding-Clothing"),
    @XmlEnumValue("CLOTHING-Womens-Clothing")
    CLOTHING_WOMENS_CLOTHING("CLOTHING-Womens-Clothing"),
    @XmlEnumValue("CLOTHING-General")
    CLOTHING_GENERAL("CLOTHING-General"),
    @XmlEnumValue("CLOTHING-Jewelry")
    CLOTHING_JEWELRY("CLOTHING-Jewelry"),
    @XmlEnumValue("CLOTHING-Watches-and-Clocks")
    CLOTHING_WATCHES_AND_CLOCKS("CLOTHING-Watches-and-Clocks"),
    @XmlEnumValue("CLOTHING-Rings")
    CLOTHING_RINGS("CLOTHING-Rings"),
    @XmlEnumValue("COLLECTIBLES-General")
    COLLECTIBLES_GENERAL("COLLECTIBLES-General"),
    @XmlEnumValue("COLLECTIBLES-Advertising")
    COLLECTIBLES_ADVERTISING("COLLECTIBLES-Advertising"),
    @XmlEnumValue("COLLECTIBLES-Animals")
    COLLECTIBLES_ANIMALS("COLLECTIBLES-Animals"),
    @XmlEnumValue("COLLECTIBLES-Animation")
    COLLECTIBLES_ANIMATION("COLLECTIBLES-Animation"),
    @XmlEnumValue("COLLECTIBLES-Coin-Operated-Banks-and-Casinos")
    COLLECTIBLES_COIN_OPERATED_BANKS_AND_CASINOS("COLLECTIBLES-Coin-Operated-Banks-and-Casinos"),
    @XmlEnumValue("COLLECTIBLES-Coins-and-Paper-Money")
    COLLECTIBLES_COINS_AND_PAPER_MONEY("COLLECTIBLES-Coins-and-Paper-Money"),
    @XmlEnumValue("COLLECTIBLES-Comics")
    COLLECTIBLES_COMICS("COLLECTIBLES-Comics"),
    @XmlEnumValue("COLLECTIBLES-Decorative")
    COLLECTIBLES_DECORATIVE("COLLECTIBLES-Decorative"),
    @XmlEnumValue("COLLECTIBLES-Disneyana")
    COLLECTIBLES_DISNEYANA("COLLECTIBLES-Disneyana"),
    @XmlEnumValue("COLLECTIBLES-Holiday")
    COLLECTIBLES_HOLIDAY("COLLECTIBLES-Holiday"),
    @XmlEnumValue("COLLECTIBLES-Knives-and-Swords")
    COLLECTIBLES_KNIVES_AND_SWORDS("COLLECTIBLES-Knives-and-Swords"),
    @XmlEnumValue("COLLECTIBLES-Militaria")
    COLLECTIBLES_MILITARIA("COLLECTIBLES-Militaria"),
    @XmlEnumValue("COLLECTIBLES-Postcards-and-Paper")
    COLLECTIBLES_POSTCARDS_AND_PAPER("COLLECTIBLES-Postcards-and-Paper"),
    @XmlEnumValue("COLLECTIBLES-Stamps")
    COLLECTIBLES_STAMPS("COLLECTIBLES-Stamps"),
    @XmlEnumValue("COMPUTERHARDWAREANDSOFTWARE-General")
    COMPUTERHARDWAREANDSOFTWARE_GENERAL("COMPUTERHARDWAREANDSOFTWARE-General"),
    @XmlEnumValue("COMPUTERHARDWAREANDSOFTWARE-Desktop-PCs")
    COMPUTERHARDWAREANDSOFTWARE_DESKTOP_P_CS("COMPUTERHARDWAREANDSOFTWARE-Desktop-PCs"),
    @XmlEnumValue("COMPUTERHARDWAREANDSOFTWARE-Monitors")
    COMPUTERHARDWAREANDSOFTWARE_MONITORS("COMPUTERHARDWAREANDSOFTWARE-Monitors"),
    @XmlEnumValue("COMPUTERHARDWAREANDSOFTWARE-Hardware")
    COMPUTERHARDWAREANDSOFTWARE_HARDWARE("COMPUTERHARDWAREANDSOFTWARE-Hardware"),
    @XmlEnumValue("COMPUTERHARDWAREANDSOFTWARE-Peripherals")
    COMPUTERHARDWAREANDSOFTWARE_PERIPHERALS("COMPUTERHARDWAREANDSOFTWARE-Peripherals"),
    @XmlEnumValue("COMPUTERHARDWAREANDSOFTWARE-Laptops-Notebooks-PDAs")
    COMPUTERHARDWAREANDSOFTWARE_LAPTOPS_NOTEBOOKS_PD_AS("COMPUTERHARDWAREANDSOFTWARE-Laptops-Notebooks-PDAs"),
    @XmlEnumValue("COMPUTERHARDWAREANDSOFTWARE-Networking-Equipment")
    COMPUTERHARDWAREANDSOFTWARE_NETWORKING_EQUIPMENT("COMPUTERHARDWAREANDSOFTWARE-Networking-Equipment"),
    @XmlEnumValue("COMPUTERHARDWAREANDSOFTWARE-Parts-and-Accessories")
    COMPUTERHARDWAREANDSOFTWARE_PARTS_AND_ACCESSORIES("COMPUTERHARDWAREANDSOFTWARE-Parts-and-Accessories"),
    @XmlEnumValue("COMPUTERHARDWAREANDSOFTWARE-GeneralSoftware")
    COMPUTERHARDWAREANDSOFTWARE_GENERAL_SOFTWARE("COMPUTERHARDWAREANDSOFTWARE-GeneralSoftware"),
    @XmlEnumValue("COMPUTERHARDWAREANDSOFTWARE-Oem-Software")
    COMPUTERHARDWAREANDSOFTWARE_OEM_SOFTWARE("COMPUTERHARDWAREANDSOFTWARE-Oem-Software"),
    @XmlEnumValue("COMPUTERHARDWAREANDSOFTWARE-Academic-Software")
    COMPUTERHARDWAREANDSOFTWARE_ACADEMIC_SOFTWARE("COMPUTERHARDWAREANDSOFTWARE-Academic-Software"),
    @XmlEnumValue("COMPUTERHARDWAREANDSOFTWARE-Beta-Software")
    COMPUTERHARDWAREANDSOFTWARE_BETA_SOFTWARE("COMPUTERHARDWAREANDSOFTWARE-Beta-Software"),
    @XmlEnumValue("COMPUTERHARDWAREANDSOFTWARE-Game-Software")
    COMPUTERHARDWAREANDSOFTWARE_GAME_SOFTWARE("COMPUTERHARDWAREANDSOFTWARE-Game-Software"),
    @XmlEnumValue("COMPUTERHARDWAREANDSOFTWARE-Data-Processing-Svc")
    COMPUTERHARDWAREANDSOFTWARE_DATA_PROCESSING_SVC("COMPUTERHARDWAREANDSOFTWARE-Data-Processing-Svc"),
    @XmlEnumValue("CULTUREANDRELIGION-General")
    CULTUREANDRELIGION_GENERAL("CULTUREANDRELIGION-General"),
    @XmlEnumValue("CULTUREANDRELIGION-Christianity")
    CULTUREANDRELIGION_CHRISTIANITY("CULTUREANDRELIGION-Christianity"),
    @XmlEnumValue("CULTUREANDRELIGION-Metaphysical")
    CULTUREANDRELIGION_METAPHYSICAL("CULTUREANDRELIGION-Metaphysical"),
    @XmlEnumValue("CULTUREANDRELIGION-New-Age")
    CULTUREANDRELIGION_NEW_AGE("CULTUREANDRELIGION-New-Age"),
    @XmlEnumValue("CULTUREANDRELIGION-Organizations")
    CULTUREANDRELIGION_ORGANIZATIONS("CULTUREANDRELIGION-Organizations"),
    @XmlEnumValue("CULTUREANDRELIGION-Other-Faiths")
    CULTUREANDRELIGION_OTHER_FAITHS("CULTUREANDRELIGION-Other-Faiths"),
    @XmlEnumValue("CULTUREANDRELIGION-Collectibles")
    CULTUREANDRELIGION_COLLECTIBLES("CULTUREANDRELIGION-Collectibles"),
    @XmlEnumValue("ELECTRONICSANDTELECOM-GeneralTelecom")
    ELECTRONICSANDTELECOM_GENERAL_TELECOM("ELECTRONICSANDTELECOM-GeneralTelecom"),
    @XmlEnumValue("ELECTRONICSANDTELECOM-Cell-Phones-and-Pagers")
    ELECTRONICSANDTELECOM_CELL_PHONES_AND_PAGERS("ELECTRONICSANDTELECOM-Cell-Phones-and-Pagers"),
    @XmlEnumValue("ELECTRONICSANDTELECOM-Telephone-Cards")
    ELECTRONICSANDTELECOM_TELEPHONE_CARDS("ELECTRONICSANDTELECOM-Telephone-Cards"),
    @XmlEnumValue("ELECTRONICSANDTELECOM-Telephone-Equipment")
    ELECTRONICSANDTELECOM_TELEPHONE_EQUIPMENT("ELECTRONICSANDTELECOM-Telephone-Equipment"),
    @XmlEnumValue("ELECTRONICSANDTELECOM-Telephone-Services")
    ELECTRONICSANDTELECOM_TELEPHONE_SERVICES("ELECTRONICSANDTELECOM-Telephone-Services"),
    @XmlEnumValue("ELECTRONICSANDTELECOM-GeneralElectronics")
    ELECTRONICSANDTELECOM_GENERAL_ELECTRONICS("ELECTRONICSANDTELECOM-GeneralElectronics"),
    @XmlEnumValue("ELECTRONICSANDTELECOM-Car-Audio-and-Electronics")
    ELECTRONICSANDTELECOM_CAR_AUDIO_AND_ELECTRONICS("ELECTRONICSANDTELECOM-Car-Audio-and-Electronics"),
    @XmlEnumValue("ELECTRONICSANDTELECOM-Home-Electronics")
    ELECTRONICSANDTELECOM_HOME_ELECTRONICS("ELECTRONICSANDTELECOM-Home-Electronics"),
    @XmlEnumValue("ELECTRONICSANDTELECOM-Home-Audio")
    ELECTRONICSANDTELECOM_HOME_AUDIO("ELECTRONICSANDTELECOM-Home-Audio"),
    @XmlEnumValue("ELECTRONICSANDTELECOM-Gadgets-and-other-electronics")
    ELECTRONICSANDTELECOM_GADGETS_AND_OTHER_ELECTRONICS("ELECTRONICSANDTELECOM-Gadgets-and-other-electronics"),
    @XmlEnumValue("ELECTRONICSANDTELECOM-Batteries")
    ELECTRONICSANDTELECOM_BATTERIES("ELECTRONICSANDTELECOM-Batteries"),
    @XmlEnumValue("ELECTRONICSANDTELECOM-ScannersRadios")
    ELECTRONICSANDTELECOM_SCANNERS_RADIOS("ELECTRONICSANDTELECOM-ScannersRadios"),
    @XmlEnumValue("ELECTRONICSANDTELECOM-Radar-Dectors")
    ELECTRONICSANDTELECOM_RADAR_DECTORS("ELECTRONICSANDTELECOM-Radar-Dectors"),
    @XmlEnumValue("ELECTRONICSANDTELECOM-Radar-Jamming-Devices")
    ELECTRONICSANDTELECOM_RADAR_JAMMING_DEVICES("ELECTRONICSANDTELECOM-Radar-Jamming-Devices"),
    @XmlEnumValue("ELECTRONICSANDTELECOM-Satellite-and-Cable-TV-Descramblers")
    ELECTRONICSANDTELECOM_SATELLITE_AND_CABLE_TV_DESCRAMBLERS("ELECTRONICSANDTELECOM-Satellite-and-Cable-TV-Descramblers"),
    @XmlEnumValue("ELECTRONICSANDTELECOM-Surveillance-Equipment")
    ELECTRONICSANDTELECOM_SURVEILLANCE_EQUIPMENT("ELECTRONICSANDTELECOM-Surveillance-Equipment"),
    @XmlEnumValue("ENTERTAINMENT-General")
    ENTERTAINMENT_GENERAL("ENTERTAINMENT-General"),
    @XmlEnumValue("ENTERTAINMENT-Movies")
    ENTERTAINMENT_MOVIES("ENTERTAINMENT-Movies"),
    @XmlEnumValue("ENTERTAINMENT-Music")
    ENTERTAINMENT_MUSIC("ENTERTAINMENT-Music"),
    @XmlEnumValue("ENTERTAINMENT-Concerts")
    ENTERTAINMENT_CONCERTS("ENTERTAINMENT-Concerts"),
    @XmlEnumValue("ENTERTAINMENT-Theater")
    ENTERTAINMENT_THEATER("ENTERTAINMENT-Theater"),
    @XmlEnumValue("ENTERTAINMENT-Bootleg-Recordings")
    ENTERTAINMENT_BOOTLEG_RECORDINGS("ENTERTAINMENT-Bootleg-Recordings"),
    @XmlEnumValue("ENTERTAINMENT-Promotional-Items")
    ENTERTAINMENT_PROMOTIONAL_ITEMS("ENTERTAINMENT-Promotional-Items"),
    @XmlEnumValue("ENTERTAINMENTMEMORABILIA-General")
    ENTERTAINMENTMEMORABILIA_GENERAL("ENTERTAINMENTMEMORABILIA-General"),
    @XmlEnumValue("ENTERTAINMENTMEMORABILIA-Autographs")
    ENTERTAINMENTMEMORABILIA_AUTOGRAPHS("ENTERTAINMENTMEMORABILIA-Autographs"),
    @XmlEnumValue("ENTERTAINMENTMEMORABILIA-Limited-Editions")
    ENTERTAINMENTMEMORABILIA_LIMITED_EDITIONS("ENTERTAINMENTMEMORABILIA-Limited-Editions"),
    @XmlEnumValue("ENTERTAINMENTMEMORABILIA-Movie")
    ENTERTAINMENTMEMORABILIA_MOVIE("ENTERTAINMENTMEMORABILIA-Movie"),
    @XmlEnumValue("ENTERTAINMENTMEMORABILIA-Music")
    ENTERTAINMENTMEMORABILIA_MUSIC("ENTERTAINMENTMEMORABILIA-Music"),
    @XmlEnumValue("ENTERTAINMENTMEMORABILIA-Novelties")
    ENTERTAINMENTMEMORABILIA_NOVELTIES("ENTERTAINMENTMEMORABILIA-Novelties"),
    @XmlEnumValue("ENTERTAINMENTMEMORABILIA-Photos")
    ENTERTAINMENTMEMORABILIA_PHOTOS("ENTERTAINMENTMEMORABILIA-Photos"),
    @XmlEnumValue("ENTERTAINMENTMEMORABILIA-Posters")
    ENTERTAINMENTMEMORABILIA_POSTERS("ENTERTAINMENTMEMORABILIA-Posters"),
    @XmlEnumValue("ENTERTAINMENTMEMORABILIA-Sports-and-Fan-Shop")
    ENTERTAINMENTMEMORABILIA_SPORTS_AND_FAN_SHOP("ENTERTAINMENTMEMORABILIA-Sports-and-Fan-Shop"),
    @XmlEnumValue("ENTERTAINMENTMEMORABILIA-Science-Fiction")
    ENTERTAINMENTMEMORABILIA_SCIENCE_FICTION("ENTERTAINMENTMEMORABILIA-Science-Fiction"),
    @XmlEnumValue("FOODDRINKANDNUTRITION-General")
    FOODDRINKANDNUTRITION_GENERAL("FOODDRINKANDNUTRITION-General"),
    @XmlEnumValue("FOODDRINKANDNUTRITION-Coffee-and-Tea")
    FOODDRINKANDNUTRITION_COFFEE_AND_TEA("FOODDRINKANDNUTRITION-Coffee-and-Tea"),
    @XmlEnumValue("FOODDRINKANDNUTRITION-Food-Products")
    FOODDRINKANDNUTRITION_FOOD_PRODUCTS("FOODDRINKANDNUTRITION-Food-Products"),
    @XmlEnumValue("FOODDRINKANDNUTRITION-Gourmet-Items")
    FOODDRINKANDNUTRITION_GOURMET_ITEMS("FOODDRINKANDNUTRITION-Gourmet-Items"),
    @XmlEnumValue("FOODDRINKANDNUTRITION-Health-and-Nutrition")
    FOODDRINKANDNUTRITION_HEALTH_AND_NUTRITION("FOODDRINKANDNUTRITION-Health-and-Nutrition"),
    @XmlEnumValue("FOODDRINKANDNUTRITION-Services")
    FOODDRINKANDNUTRITION_SERVICES("FOODDRINKANDNUTRITION-Services"),
    @XmlEnumValue("FOODDRINKANDNUTRITION-Vitamins-and-Supplements")
    FOODDRINKANDNUTRITION_VITAMINS_AND_SUPPLEMENTS("FOODDRINKANDNUTRITION-Vitamins-and-Supplements"),
    @XmlEnumValue("FOODDRINKANDNUTRITION-Weight-Management-and-Health-Products")
    FOODDRINKANDNUTRITION_WEIGHT_MANAGEMENT_AND_HEALTH_PRODUCTS("FOODDRINKANDNUTRITION-Weight-Management-and-Health-Products"),
    @XmlEnumValue("FOODDRINKANDNUTRITION-Restaurant")
    FOODDRINKANDNUTRITION_RESTAURANT("FOODDRINKANDNUTRITION-Restaurant"),
    @XmlEnumValue("FOODDRINKANDNUTRITION-Tobacco-and-Cigars")
    FOODDRINKANDNUTRITION_TOBACCO_AND_CIGARS("FOODDRINKANDNUTRITION-Tobacco-and-Cigars"),
    @XmlEnumValue("FOODDRINKANDNUTRITION-Alcoholic-Beverages")
    FOODDRINKANDNUTRITION_ALCOHOLIC_BEVERAGES("FOODDRINKANDNUTRITION-Alcoholic-Beverages"),
    @XmlEnumValue("GIFTSANDFLOWERS-General")
    GIFTSANDFLOWERS_GENERAL("GIFTSANDFLOWERS-General"),
    @XmlEnumValue("GIFTSANDFLOWERS-Flowers")
    GIFTSANDFLOWERS_FLOWERS("GIFTSANDFLOWERS-Flowers"),
    @XmlEnumValue("GIFTSANDFLOWERS-Greeting-Cards")
    GIFTSANDFLOWERS_GREETING_CARDS("GIFTSANDFLOWERS-Greeting-Cards"),
    @XmlEnumValue("GIFTSANDFLOWERS-Humorous-Gifts-and-Novelties")
    GIFTSANDFLOWERS_HUMOROUS_GIFTS_AND_NOVELTIES("GIFTSANDFLOWERS-Humorous-Gifts-and-Novelties"),
    @XmlEnumValue("GIFTSANDFLOWERS-Personalized-Gifts")
    GIFTSANDFLOWERS_PERSONALIZED_GIFTS("GIFTSANDFLOWERS-Personalized-Gifts"),
    @XmlEnumValue("GIFTSANDFLOWERS-Products")
    GIFTSANDFLOWERS_PRODUCTS("GIFTSANDFLOWERS-Products"),
    @XmlEnumValue("GIFTSANDFLOWERS-Services")
    GIFTSANDFLOWERS_SERVICES("GIFTSANDFLOWERS-Services"),
    @XmlEnumValue("HOBBIESTOYSANDGAMES-General")
    HOBBIESTOYSANDGAMES_GENERAL("HOBBIESTOYSANDGAMES-General"),
    @XmlEnumValue("HOBBIESTOYSANDGAMES-Action-Figures")
    HOBBIESTOYSANDGAMES_ACTION_FIGURES("HOBBIESTOYSANDGAMES-Action-Figures"),
    @XmlEnumValue("HOBBIESTOYSANDGAMES-Bean-Babies")
    HOBBIESTOYSANDGAMES_BEAN_BABIES("HOBBIESTOYSANDGAMES-Bean-Babies"),
    @XmlEnumValue("HOBBIESTOYSANDGAMES-Barbies")
    HOBBIESTOYSANDGAMES_BARBIES("HOBBIESTOYSANDGAMES-Barbies"),
    @XmlEnumValue("HOBBIESTOYSANDGAMES-Bears")
    HOBBIESTOYSANDGAMES_BEARS("HOBBIESTOYSANDGAMES-Bears"),
    @XmlEnumValue("HOBBIESTOYSANDGAMES-Dolls")
    HOBBIESTOYSANDGAMES_DOLLS("HOBBIESTOYSANDGAMES-Dolls"),
    @XmlEnumValue("HOBBIESTOYSANDGAMES-Games")
    HOBBIESTOYSANDGAMES_GAMES("HOBBIESTOYSANDGAMES-Games"),
    @XmlEnumValue("HOBBIESTOYSANDGAMES-Model-Kits")
    HOBBIESTOYSANDGAMES_MODEL_KITS("HOBBIESTOYSANDGAMES-Model-Kits"),
    @XmlEnumValue("HOBBIESTOYSANDGAMES-Diecast-Toys-Vehicles")
    HOBBIESTOYSANDGAMES_DIECAST_TOYS_VEHICLES("HOBBIESTOYSANDGAMES-Diecast-Toys-Vehicles"),
    @XmlEnumValue("HOBBIESTOYSANDGAMES-Video-Games-and-Systems")
    HOBBIESTOYSANDGAMES_VIDEO_GAMES_AND_SYSTEMS("HOBBIESTOYSANDGAMES-Video-Games-and-Systems"),
    @XmlEnumValue("HOBBIESTOYSANDGAMES-Vintage-and-Antique-Toys")
    HOBBIESTOYSANDGAMES_VINTAGE_AND_ANTIQUE_TOYS("HOBBIESTOYSANDGAMES-Vintage-and-Antique-Toys"),
    @XmlEnumValue("HOBBIESTOYSANDGAMES-BackupUnreleased-Games")
    HOBBIESTOYSANDGAMES_BACKUP_UNRELEASED_GAMES("HOBBIESTOYSANDGAMES-BackupUnreleased-Games"),
    @XmlEnumValue("HOBBIESTOYSANDGAMES-Game-copying-hardwaresoftware")
    HOBBIESTOYSANDGAMES_GAME_COPYING_HARDWARESOFTWARE("HOBBIESTOYSANDGAMES-Game-copying-hardwaresoftware"),
    @XmlEnumValue("HOBBIESTOYSANDGAMES-Mod-Chips")
    HOBBIESTOYSANDGAMES_MOD_CHIPS("HOBBIESTOYSANDGAMES-Mod-Chips"),
    @XmlEnumValue("HOMEANDGARDEN-General")
    HOMEANDGARDEN_GENERAL("HOMEANDGARDEN-General"),
    @XmlEnumValue("HOMEANDGARDEN-Appliances")
    HOMEANDGARDEN_APPLIANCES("HOMEANDGARDEN-Appliances"),
    @XmlEnumValue("HOMEANDGARDEN-Bed-and-Bath")
    HOMEANDGARDEN_BED_AND_BATH("HOMEANDGARDEN-Bed-and-Bath"),
    @XmlEnumValue("HOMEANDGARDEN-Furnishing-and-Decorating")
    HOMEANDGARDEN_FURNISHING_AND_DECORATING("HOMEANDGARDEN-Furnishing-and-Decorating"),
    @XmlEnumValue("HOMEANDGARDEN-Garden-Supplies")
    HOMEANDGARDEN_GARDEN_SUPPLIES("HOMEANDGARDEN-Garden-Supplies"),
    @XmlEnumValue("HOMEANDGARDEN-Hardware-and-Tools")
    HOMEANDGARDEN_HARDWARE_AND_TOOLS("HOMEANDGARDEN-Hardware-and-Tools"),
    @XmlEnumValue("HOMEANDGARDEN-Household-Goods")
    HOMEANDGARDEN_HOUSEHOLD_GOODS("HOMEANDGARDEN-Household-Goods"),
    @XmlEnumValue("HOMEANDGARDEN-Kitchenware")
    HOMEANDGARDEN_KITCHENWARE("HOMEANDGARDEN-Kitchenware"),
    @XmlEnumValue("HOMEANDGARDEN-Rugs-and-Carpets")
    HOMEANDGARDEN_RUGS_AND_CARPETS("HOMEANDGARDEN-Rugs-and-Carpets"),
    @XmlEnumValue("HOMEANDGARDEN-Security-and-Home-Defense")
    HOMEANDGARDEN_SECURITY_AND_HOME_DEFENSE("HOMEANDGARDEN-Security-and-Home-Defense"),
    @XmlEnumValue("HOMEANDGARDEN-Plants-and-Seeds")
    HOMEANDGARDEN_PLANTS_AND_SEEDS("HOMEANDGARDEN-Plants-and-Seeds"),
    @XmlEnumValue("INTERNETANDNETWORKSERVICES-General")
    INTERNETANDNETWORKSERVICES_GENERAL("INTERNETANDNETWORKSERVICES-General"),
    @XmlEnumValue("INTERNETANDNETWORKSERVICES-Bulletin-board")
    INTERNETANDNETWORKSERVICES_BULLETIN_BOARD("INTERNETANDNETWORKSERVICES-Bulletin-board"),
    @XmlEnumValue("INTERNETANDNETWORKSERVICES-online-services")
    INTERNETANDNETWORKSERVICES_ONLINE_SERVICES("INTERNETANDNETWORKSERVICES-online-services"),
    @XmlEnumValue("INTERNETANDNETWORKSERVICES-Auction-management-tools")
    INTERNETANDNETWORKSERVICES_AUCTION_MANAGEMENT_TOOLS("INTERNETANDNETWORKSERVICES-Auction-management-tools"),
    @XmlEnumValue("INTERNETANDNETWORKSERVICES-ecommerce-development")
    INTERNETANDNETWORKSERVICES_ECOMMERCE_DEVELOPMENT("INTERNETANDNETWORKSERVICES-ecommerce-development"),
    @XmlEnumValue("INTERNETANDNETWORKSERVICES-training-services")
    INTERNETANDNETWORKSERVICES_TRAINING_SERVICES("INTERNETANDNETWORKSERVICES-training-services"),
    @XmlEnumValue("INTERNETANDNETWORKSERVICES-Online-Malls")
    INTERNETANDNETWORKSERVICES_ONLINE_MALLS("INTERNETANDNETWORKSERVICES-Online-Malls"),
    @XmlEnumValue("INTERNETANDNETWORKSERVICES-Web-hosting-and-design")
    INTERNETANDNETWORKSERVICES_WEB_HOSTING_AND_DESIGN("INTERNETANDNETWORKSERVICES-Web-hosting-and-design"),
    @XmlEnumValue("MEDIAANDENTERTAINMENT-General")
    MEDIAANDENTERTAINMENT_GENERAL("MEDIAANDENTERTAINMENT-General"),
    @XmlEnumValue("MEDIAANDENTERTAINMENT-Concerts")
    MEDIAANDENTERTAINMENT_CONCERTS("MEDIAANDENTERTAINMENT-Concerts"),
    @XmlEnumValue("MEDIAANDENTERTAINMENT-Theater")
    MEDIAANDENTERTAINMENT_THEATER("MEDIAANDENTERTAINMENT-Theater"),
    @XmlEnumValue("MEDICALANDPHARMACEUTICAL-General")
    MEDICALANDPHARMACEUTICAL_GENERAL("MEDICALANDPHARMACEUTICAL-General"),
    @XmlEnumValue("MEDICALANDPHARMACEUTICAL-Medical")
    MEDICALANDPHARMACEUTICAL_MEDICAL("MEDICALANDPHARMACEUTICAL-Medical"),
    @XmlEnumValue("MEDICALANDPHARMACEUTICAL-Dental")
    MEDICALANDPHARMACEUTICAL_DENTAL("MEDICALANDPHARMACEUTICAL-Dental"),
    @XmlEnumValue("MEDICALANDPHARMACEUTICAL-Opthamalic")
    MEDICALANDPHARMACEUTICAL_OPTHAMALIC("MEDICALANDPHARMACEUTICAL-Opthamalic"),
    @XmlEnumValue("MEDICALANDPHARMACEUTICAL-Prescription-Drugs")
    MEDICALANDPHARMACEUTICAL_PRESCRIPTION_DRUGS("MEDICALANDPHARMACEUTICAL-Prescription-Drugs"),
    @XmlEnumValue("MEDICALANDPHARMACEUTICAL-Devices")
    MEDICALANDPHARMACEUTICAL_DEVICES("MEDICALANDPHARMACEUTICAL-Devices"),
    @XmlEnumValue("MONEYSERVICEBUSINESSES-General")
    MONEYSERVICEBUSINESSES_GENERAL("MONEYSERVICEBUSINESSES-General"),
    @XmlEnumValue("MONEYSERVICEBUSINESSES-Remittance")
    MONEYSERVICEBUSINESSES_REMITTANCE("MONEYSERVICEBUSINESSES-Remittance"),
    @XmlEnumValue("MONEYSERVICEBUSINESSES-Wire-Transfer")
    MONEYSERVICEBUSINESSES_WIRE_TRANSFER("MONEYSERVICEBUSINESSES-Wire-Transfer"),
    @XmlEnumValue("MONEYSERVICEBUSINESSES-Money-Orders")
    MONEYSERVICEBUSINESSES_MONEY_ORDERS("MONEYSERVICEBUSINESSES-Money-Orders"),
    @XmlEnumValue("MONEYSERVICEBUSINESSES-Electronic-Cash")
    MONEYSERVICEBUSINESSES_ELECTRONIC_CASH("MONEYSERVICEBUSINESSES-Electronic-Cash"),
    @XmlEnumValue("MONEYSERVICEBUSINESSES-Currency-DealerExchange")
    MONEYSERVICEBUSINESSES_CURRENCY_DEALER_EXCHANGE("MONEYSERVICEBUSINESSES-Currency-DealerExchange"),
    @XmlEnumValue("MONEYSERVICEBUSINESSES-Check-Cashier")
    MONEYSERVICEBUSINESSES_CHECK_CASHIER("MONEYSERVICEBUSINESSES-Check-Cashier"),
    @XmlEnumValue("MONEYSERVICEBUSINESSES-Travelers-Checks")
    MONEYSERVICEBUSINESSES_TRAVELERS_CHECKS("MONEYSERVICEBUSINESSES-Travelers-Checks"),
    @XmlEnumValue("MONEYSERVICEBUSINESSES-Stored-Value-Cards")
    MONEYSERVICEBUSINESSES_STORED_VALUE_CARDS("MONEYSERVICEBUSINESSES-Stored-Value-Cards"),
    @XmlEnumValue("NONPROFITPOLITICALANDRELIGION-General")
    NONPROFITPOLITICALANDRELIGION_GENERAL("NONPROFITPOLITICALANDRELIGION-General"),
    @XmlEnumValue("NONPROFITPOLITICALANDRELIGION-Charities")
    NONPROFITPOLITICALANDRELIGION_CHARITIES("NONPROFITPOLITICALANDRELIGION-Charities"),
    @XmlEnumValue("NONPROFITPOLITICALANDRELIGION-Political")
    NONPROFITPOLITICALANDRELIGION_POLITICAL("NONPROFITPOLITICALANDRELIGION-Political"),
    @XmlEnumValue("NONPROFITPOLITICALANDRELIGION-Religious")
    NONPROFITPOLITICALANDRELIGION_RELIGIOUS("NONPROFITPOLITICALANDRELIGION-Religious"),
    @XmlEnumValue("PETSANDANIMALS-General")
    PETSANDANIMALS_GENERAL("PETSANDANIMALS-General"),
    @XmlEnumValue("PETSANDANIMALS-Supplies-and-Toys")
    PETSANDANIMALS_SUPPLIES_AND_TOYS("PETSANDANIMALS-Supplies-and-Toys"),
    @XmlEnumValue("PETSANDANIMALS-Wildlife-Products")
    PETSANDANIMALS_WILDLIFE_PRODUCTS("PETSANDANIMALS-Wildlife-Products"),
    @XmlEnumValue("REALESTATE-General")
    REALESTATE_GENERAL("REALESTATE-General"),
    @XmlEnumValue("REALESTATE-Commercial")
    REALESTATE_COMMERCIAL("REALESTATE-Commercial"),
    @XmlEnumValue("REALESTATE-Residential")
    REALESTATE_RESIDENTIAL("REALESTATE-Residential"),
    @XmlEnumValue("REALESTATE-Time-Shares")
    REALESTATE_TIME_SHARES("REALESTATE-Time-Shares"),
    @XmlEnumValue("SERVICES-GeneralGovernment")
    SERVICES_GENERAL_GOVERNMENT("SERVICES-GeneralGovernment"),
    @XmlEnumValue("SERVICES-Legal")
    SERVICES_LEGAL("SERVICES-Legal"),
    @XmlEnumValue("SERVICES-Medical")
    SERVICES_MEDICAL("SERVICES-Medical"),
    @XmlEnumValue("SERVICES-Dental")
    SERVICES_DENTAL("SERVICES-Dental"),
    @XmlEnumValue("SERVICES-Vision")
    SERVICES_VISION("SERVICES-Vision"),
    @XmlEnumValue("SERVICES-General")
    SERVICES_GENERAL("SERVICES-General"),
    @XmlEnumValue("SERVICES-Child-Care-Services")
    SERVICES_CHILD_CARE_SERVICES("SERVICES-Child-Care-Services"),
    @XmlEnumValue("SERVICES-Consulting")
    SERVICES_CONSULTING("SERVICES-Consulting"),
    @XmlEnumValue("SERVICES-ImportingExporting")
    SERVICES_IMPORTING_EXPORTING("SERVICES-ImportingExporting"),
    @XmlEnumValue("SERVICES-InsuranceDirect")
    SERVICES_INSURANCE_DIRECT("SERVICES-InsuranceDirect"),
    @XmlEnumValue("SERVICES-Financial-Services")
    SERVICES_FINANCIAL_SERVICES("SERVICES-Financial-Services"),
    @XmlEnumValue("SERVICES-Graphic-and-Commercial-Design")
    SERVICES_GRAPHIC_AND_COMMERCIAL_DESIGN("SERVICES-Graphic-and-Commercial-Design"),
    @XmlEnumValue("SERVICES-Landscaping")
    SERVICES_LANDSCAPING("SERVICES-Landscaping"),
    @XmlEnumValue("SERVICES-Locksmith")
    SERVICES_LOCKSMITH("SERVICES-Locksmith"),
    @XmlEnumValue("SERVICES-Online-Dating")
    SERVICES_ONLINE_DATING("SERVICES-Online-Dating"),
    @XmlEnumValue("SERVICES-Event-and-Wedding-Planning")
    SERVICES_EVENT_AND_WEDDING_PLANNING("SERVICES-Event-and-Wedding-Planning"),
    @XmlEnumValue("SERVICES-Schools-and-Colleges")
    SERVICES_SCHOOLS_AND_COLLEGES("SERVICES-Schools-and-Colleges"),
    @XmlEnumValue("SERVICES-Entertainment")
    SERVICES_ENTERTAINMENT("SERVICES-Entertainment"),
    @XmlEnumValue("SERVICES-Aggregators")
    SERVICES_AGGREGATORS("SERVICES-Aggregators"),
    @XmlEnumValue("SPORTSANDRECREATION-General")
    SPORTSANDRECREATION_GENERAL("SPORTSANDRECREATION-General"),
    @XmlEnumValue("SPORTSANDRECREATION-Bicycles-and-Accessories")
    SPORTSANDRECREATION_BICYCLES_AND_ACCESSORIES("SPORTSANDRECREATION-Bicycles-and-Accessories"),
    @XmlEnumValue("SPORTSANDRECREATION-Boating-Sailing-and-Accessories")
    SPORTSANDRECREATION_BOATING_SAILING_AND_ACCESSORIES("SPORTSANDRECREATION-Boating-Sailing-and-Accessories"),
    @XmlEnumValue("SPORTSANDRECREATION-Camping-and-Survival")
    SPORTSANDRECREATION_CAMPING_AND_SURVIVAL("SPORTSANDRECREATION-Camping-and-Survival"),
    @XmlEnumValue("SPORTSANDRECREATION-Exercise-Equipment")
    SPORTSANDRECREATION_EXERCISE_EQUIPMENT("SPORTSANDRECREATION-Exercise-Equipment"),
    @XmlEnumValue("SPORTSANDRECREATION-Fishing")
    SPORTSANDRECREATION_FISHING("SPORTSANDRECREATION-Fishing"),
    @XmlEnumValue("SPORTSANDRECREATION-Golf")
    SPORTSANDRECREATION_GOLF("SPORTSANDRECREATION-Golf"),
    @XmlEnumValue("SPORTSANDRECREATION-Hunting")
    SPORTSANDRECREATION_HUNTING("SPORTSANDRECREATION-Hunting"),
    @XmlEnumValue("SPORTSANDRECREATION-Paintball")
    SPORTSANDRECREATION_PAINTBALL("SPORTSANDRECREATION-Paintball"),
    @XmlEnumValue("SPORTSANDRECREATION-Sporting-Goods")
    SPORTSANDRECREATION_SPORTING_GOODS("SPORTSANDRECREATION-Sporting-Goods"),
    @XmlEnumValue("SPORTSANDRECREATION-Swimming-Pools-and-Spas")
    SPORTSANDRECREATION_SWIMMING_POOLS_AND_SPAS("SPORTSANDRECREATION-Swimming-Pools-and-Spas"),
    @XmlEnumValue("TRAVEL-General")
    TRAVEL_GENERAL("TRAVEL-General"),
    @XmlEnumValue("TRAVEL-Accommodations")
    TRAVEL_ACCOMMODATIONS("TRAVEL-Accommodations"),
    @XmlEnumValue("TRAVEL-Agencies")
    TRAVEL_AGENCIES("TRAVEL-Agencies"),
    @XmlEnumValue("TRAVEL-Airlines")
    TRAVEL_AIRLINES("TRAVEL-Airlines"),
    @XmlEnumValue("TRAVEL-Auto-Rentals")
    TRAVEL_AUTO_RENTALS("TRAVEL-Auto-Rentals"),
    @XmlEnumValue("TRAVEL-Cruises")
    TRAVEL_CRUISES("TRAVEL-Cruises"),
    @XmlEnumValue("TRAVEL-Other-Transportation")
    TRAVEL_OTHER_TRANSPORTATION("TRAVEL-Other-Transportation"),
    @XmlEnumValue("TRAVEL-Services")
    TRAVEL_SERVICES("TRAVEL-Services"),
    @XmlEnumValue("TRAVEL-Supplies")
    TRAVEL_SUPPLIES("TRAVEL-Supplies"),
    @XmlEnumValue("TRAVEL-Tours")
    TRAVEL_TOURS("TRAVEL-Tours"),
    @XmlEnumValue("TRAVEL-AirlinesSpirit-Air")
    TRAVEL_AIRLINES_SPIRIT_AIR("TRAVEL-AirlinesSpirit-Air"),
    @XmlEnumValue("Other-SubCategories")
    OTHER_SUB_CATEGORIES("Other-SubCategories");
    private final String value;

    BusinessSubCategoryType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BusinessSubCategoryType fromValue(String v) {
        for (BusinessSubCategoryType c: BusinessSubCategoryType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
