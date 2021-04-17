# Proyecto Final Ada :ok_woman: :tada:
*Actualmente en progreso*

## Consigna :alarm_clock: :hatching_chick:
En este proyecto vas a crear una aplicación/servicio REST que permita gestionar becas y cupos de estudio de cursos, en diferentes categorías

## Funcionalidades principales :computer: :books: :pencil2:
1. Los cursos son publicados por organizaciones que deben estar registradas en el sistema y aprobadas por un usuario administrador. Mientras la organización no tenga dicha aprobación, no podrá cargar los cursos que ofrece

2. Los datos de la organización en el sistema de ser:
  - Nombre de organización
  - Cuil
  - Tipo de organización
  - Dirección de la organización
  - Categoría de la organización
  - Año de fundación
  - Número de contacto
  - Representante:
    - Nombre y apellido
    - Tipo y número de documento
    - Cargo en la organizació 
    - Email

3. Los cursos que ofrece cada organización, sólo pueden ser cargados por representantes de dicha organización. Los datos de cada curso son:
  - Nombre del curso
  - Descripción del curso
  - Modalidad del curso
  - Costo del curso
  - Horas del curso
  - Categoría del curso
  - Número de participantes
  - Número de cupos con beca
    
4. Las solicitudes de inscripción son realizadas por las personas inscriptas en la plataforma. Una vez que se alcanza el número de participantes por curso, no se podrá hacer más solicitudes de inscripción. Hay 2 tipos de solicitudes de inscripción. Las personas participantes pueden:
  - Costear el total del curso (adjudicación directa del cupo)
  - Solicitar una beca de estudio. Las solicitudes por becas no afectan el número de cupos hasta que ésta no haya sido aprobada por un admin, a diferencia de las solicitudes de adjudicación directa

5. Para realizar un registro como participante, se deben llenar los siguientes datos:
- Nombre y Apellido
- Fecha de nacimiento
- Lugar de vivienda
- Datos Socio-Económico:
    - Estudia?
    - Trabaja?
    - Tiene ingresos? Cantidad mensual
    - Familia a cargo? Cuantos?
 
6. Al momento que una persona haga la solicitud de inscripción a un curso, debe indicar si es con pago al 100% ó a través de una beca. En caso de que sea por beca, debe tener los datos Socio-Económico registrados en el sistema y no debe tener un curso en progreso con beca adjudicado.
La aprobación de las becas está a cargo del admin del sistema y este podrá adjudicar un 50%, 75% o 100% de la beca o en su defecto denegar la misma

7. El admin también tiene la responsabilidad de indicar si la persona ha finalizado un curso

8. El motor de búsqueda de cursos debe contemplar los siguientes criterios:
  - Todos los cursos disponibles (con cupos abiertos)
  - Todos los cursos por categoría
  - Todos los cursos por organización
  - Todos los cursos por participante (en progreso)
  - Todos los cursos por participante (finalizados)
  - Todos los cursos por organización y categoría

## Competencias :thumbsup: :1st_place_medal: :trophy:
- Eclipse - IDE
- Java- Paradigma de objetos
- Desarrollo de APIs REST: Spring / Spring Boot / Spring Security / Maven / Hibernate
- Testing de app - Junit
- Base de datos (SQL)
