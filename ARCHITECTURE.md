# COMP3350 G09 Iteration 2
## MusicLoud Architecture

### Packages
* Application (com.example.musicloud.application)
* Business (com.example.musicloud.business)
* Objects (com.example.musicloud.objects)
* Persistence
    * HSQLDB (com.example.musicloud.persistence.hsqldb)
    * Stubs (com.example.musicloud.persistence.stubs)
* Presentation (com.example.musicloud.presentation)
* Tests
    * Business
    * Objects
    * Persistence

### Layers
| Presentation/UI | Logic/Business | Persistence/Data|
|-----------------|----------------|-----------------|
| Login           | LoginManager   | UserManagement  |
| Regsiter        | AccessUsers    | UserManagement  |
| PlayActivity    | AccessSongs    | SongPersistence |
| MediaPlayerUtil | AccessSongs    | UserManagement  |

### Overview
### Three tiered architecture:

We have untilized a multi-tiered system to develop our application. This includes three main tiers: `Persistance`, `Business`, and `Presentation`. The communication between these packages is strict in order to maintian the stucture of our code. 

[![](https://mermaid.ink/img/pako:eNpNj7EOwjAMRH8l8gw_kKEDYkWqxOrFalyI1DgQOwOq-u-kRRXd7Hvnk2-GIQcGD8rvyjLwNdKjUELpCyuLkcUs5667VI3Cqt6h7HOTj64jco25notGtTV2ZYd1w_9IOEHikiiG9smM4hyCPTkxQjuEwCPVyRBQlmalavn-kQG8lconqK9Atj8OfqRJm8ohWi63X7ut5PIFeqZTbw?type=png)](https://mermaid.live/edit#pako:eNpNj7EOwjAMRH8l8gw_kKEDYkWqxOrFalyI1DgQOwOq-u-kRRXd7Hvnk2-GIQcGD8rvyjLwNdKjUELpCyuLkcUs5667VI3Cqt6h7HOTj64jco25notGtTV2ZYd1w_9IOEHikiiG9smM4hyCPTkxQjuEwCPVyRBQlmalavn-kQG8lconqK9Atj8OfqRJm8ohWi63X7ut5PIFeqZTbw)


## Diagram

### Presentation
- Presentation builds the log-in interface using `LoginActivity`. 
- For new users, the `RegisterActivity` is run in order to create new login credentials. 
- `PlayActivity` is responsible for facilitating the interface to play each song. 
- `MediaPlayerUtil` is resposible for managing the songs and keeping them in order. 


### Business
- At the moment business handles user accounts and access within the `AccessUsers` class.
- `Access Songs` is responsible for accessing the songs form the database.
- `LoginManager` is responsible for verifying the users i.e. matching the credentials form the database.
- `ValidationInput` makes sure that Users enter valid information while creating an account.

### Persistance
Included in the persistance layer is UserManagement, UserManagementStub, SongPersistence, SongPersistenceStub and UserManagementHSQLDB.
- `UserManagement` is the interface that is implemented by `UserManagementStubs` and `UserManagementHSQLDB`.
- `SongPersistence` is the interface that is implemented by `SongPersistenceStubs` and `SongPersistenceHSQLDB`.

[![](https://mermaid.ink/img/pako:eNqFkbFuwzAMRH-F0Jz8gIYMRdcAAbpqISTaIWBTqUilKIL8e2kngTu1GyHd3SOOt5BroRCD0mcnyfTOODack5waKYmhcZX94fDWlYVUI3SlBrkRGilgzrWLJXn9g2vhRE1ZbcmLoHhlGUHo66WGQoY86X8QNKP5YgpTHYHlTwhhy2dfaKjtYWbxcV6TnbNpV-8GulLjgd24mDaCi37vtgCkKOQqAz9Twy7M5DMXr--WBCAFO9NMKUQfCw3YJ0shyd2l2K1-fEsO0VqnXeiX4gU-2w5xwEn9lQpbbcfHSdbL3H8A1meYwA?type=png)](https://mermaid.live/edit#pako:eNqFkbFuwzAMRH-F0Jz8gIYMRdcAAbpqISTaIWBTqUilKIL8e2kngTu1GyHd3SOOt5BroRCD0mcnyfTOODack5waKYmhcZX94fDWlYVUI3SlBrkRGilgzrWLJXn9g2vhRE1ZbcmLoHhlGUHo66WGQoY86X8QNKP5YgpTHYHlTwhhy2dfaKjtYWbxcV6TnbNpV-8GulLjgd24mDaCi37vtgCkKOQqAz9Twy7M5DMXr--WBCAFO9NMKUQfCw3YJ0shyd2l2K1-fEsO0VqnXeiX4gU-2w5xwEn9lQpbbcfHSdbL3H8A1meYwA)

## Branching Strategy
We've been using Modified GitHub Flow branching strategy because it allows us to work on seperate features and user stories in seperate branches. At the end of the iteration, we merge respective branches into the main branch which is then ready for release.


