#README FILE
## Variant 3
### Topic: *Electricity Billing System*
### Made by: *Aida Marat*
### Group: *ITSE-1909*

* Created database (data.sql, schema.sql). Created 10 classes and one join table.
    * One-to-One (Customer, PaymentCard)
    * One-to-Many (City, Station)
    * Many-to-Many (Manager, Station, Stationmanager)
* Used different type of bean annotation @Service, @Repository, @Component
* Wrote good Service logic (CheckService)
* Used 
  * @PropertySource(ApplicationConfig)
  * @Lazy(ApplicationConfig) 
  * @Scope(ApplicationConfig)
  * @DependsOn(ApplicationConfig)
* Have 2 configuration classes 
  * AplicationConfig 
  * JmsConfig
* Added AOP configuration. Used AspectJ annotation style. Done log information for methods
  * @Before (OtherLogger)
  * @Pointcut (OtherLogger)
  * @After (OtherLogger)
  * @Aspect (OtherLogger)
  * @AfterReturning (OtherLogger)
  * @Around (MethodLogging)
  * @AfterThrowing.(MethodLogging)
* Implemented repositories with JdbcTemplate class
* Implemented batch operations (FirmService)
* Implemented:
  * Converter
  * Validation (used @AssertTrue)
  * Formatter
* Used @Scheduled annotation. Also project has sceduledTaskTime and spring.task.scheduling.pool.size
  * fixedDelay (ApplicationConfig)
  * fixedRate (ApplicationConfig)
  * initialDelay (ApplicationConfig)
* Used Http Methods: GET, HEAD, POST, PUT, OPTIONS, DELETE 
* Used next annotations: @GetMapping, @PostMapping, @PutMapping, @DeleteMapping (Controllers)
* Used next annotations:
  * @RequestBody (CustomerController)
  * @ResponseBody (CustomerController)
  * @ResponseStatus (CheckController)
  * @ResponseEntity (CustomerController)
  * @RequestHeader (CustomerController)
* Added REST Pagination support (CustomerController)
* Added Upload/Download files (FileController)
* Implemented JMS Service
* Implemented Basic Security
* Prevent Brute Force Authentication Attempts with Spring Security
* Control the Session with Spring Security
* Fixed 401s with CORS Preflights and Spring Security (WebSecurityConfig)
* Prevented Cross-Site Scripting (commented in WebSecurityConfig)
* Added Websocket 


  