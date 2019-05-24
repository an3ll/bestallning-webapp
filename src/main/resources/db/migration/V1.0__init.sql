create table handlaggare(
  id serial,
  fullstandigt_namn varchar (255),
  telefonnummer varchar (255),
  myndighet varchar (255) not null,
  kontor varchar (255),
  adress varchar (255),
  postnummer varchar (255),
  stad varchar (255),
  kostnadsstalle varchar (255),

  primary key(id)
);

create table bestallning(
  id serial,
  typ varchar (255) not null,
  intyg_typ varchar (255) not null,
  intyg_typ_beskrivning varchar (255) not null,
  ankomst_datum timestamp not null,
  ankomst_datum_string varchar (255) not null,
  avslut_datum timestamp,
  syfte varchar (255),
  arende_referens varchar (255),
  planerade_aktiviteter varchar (255),
  status varchar (255),
  status_string varchar (255),
  invanare_person_id varchar (255),
  invanare_bakgrund_nulage varchar (255),
  handlaggare_id integer references handlaggare(id),
  vardenhet_hsa_id varchar (255),
  vardenhet_vardgivare_hsa_id varchar (255),
  vardenhet_vardgivare_organisation_id varchar (255),
  vardenhet_namn varchar (255),
  vardenhet_epost varchar (255),

  primary key(id)
);
