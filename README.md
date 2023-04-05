# string-of-pearls-graphql
Personal tool for the mind.

## Idea
Every action in the day, is a pearl this action is not perfect, we can identify a turd but we don't really know whether this turd is good or bad, we don't judge turds.
The idea is to track all the actions during the day that way you can see all the things we acomplish in a day, with its ups and downs.

Based on the string of pearls tools in the [Stuz doc](https://www.youtube.com/watch?v=UKCmefQdplI)

### Docker

Create secret in docker: https://docs.docker.com/engine/swarm/secrets/

```
docker stack deploy --compose-file docker-compose.yml string_of_pearls
```

### Vault database configuration

https://developer.hashicorp.com/vault/tutorials/db-credentials/database-secrets#configure-postgresql-secrets-engine


### Table

```sql
create table string_of_pearls (
  creation_date date not null,
  notes text,
  turd text,
  name text
);
```
