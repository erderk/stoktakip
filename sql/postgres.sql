PGDMP                         x            postgres    12.3    12.3 $    5           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            6           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            7           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            8           1262    13318    postgres    DATABASE     �   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Turkish_Turkey.1254' LC_CTYPE = 'Turkish_Turkey.1254';
    DROP DATABASE postgres;
                postgres    false            9           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                   postgres    false    2872                        3079    16384 	   adminpack 	   EXTENSION     A   CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;
    DROP EXTENSION adminpack;
                   false            :           0    0    EXTENSION adminpack    COMMENT     M   COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';
                        false    1            �            1255    16514    depofunction()    FUNCTION     �   CREATE FUNCTION public.depofunction() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
 BEGIN
   New.depoid:=nextval('deposeq');
   Return NEW;
 END;
 $$;
 %   DROP FUNCTION public.depofunction();
       public          postgres    false            �            1255    16441    kullanicifunction()    FUNCTION     �   CREATE FUNCTION public.kullanicifunction() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
 BEGIN
   New.id:=nextval('kullaniciseq');
   Return NEW;
 END;
 $$;
 *   DROP FUNCTION public.kullanicifunction();
       public          postgres    false            �            1255    16516    urunfunction()    FUNCTION     �   CREATE FUNCTION public.urunfunction() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
 BEGIN
   New.urunid:=nextval('urunseq');
   Return NEW;
 END;
 $$;
 %   DROP FUNCTION public.urunfunction();
       public          postgres    false            �            1259    16497    adet    TABLE     i   CREATE TABLE public.adet (
    depoid integer NOT NULL,
    urunid integer NOT NULL,
    adet integer
);
    DROP TABLE public.adet;
       public         heap    postgres    false            �            1259    16481    depo    TABLE     c   CREATE TABLE public.depo (
    depoid integer NOT NULL,
    depoadi text,
    depoaciklama text
);
    DROP TABLE public.depo;
       public         heap    postgres    false            �            1259    16507    deposeq    SEQUENCE     r   CREATE SEQUENCE public.deposeq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 1000
    CACHE 1;
    DROP SEQUENCE public.deposeq;
       public          postgres    false            �            1259    16509    kuldep    TABLE     l   CREATE TABLE public.kuldep (
    depoid integer NOT NULL,
    id integer NOT NULL,
    calisiyor boolean
);
    DROP TABLE public.kuldep;
       public         heap    postgres    false            �            1259    16463 	   kullanici    TABLE     y   CREATE TABLE public.kullanici (
    id integer NOT NULL,
    ad text,
    soyad text,
    eposta text,
    sifre text
);
    DROP TABLE public.kullanici;
       public         heap    postgres    false            �            1259    16398    kullaniciseq    SEQUENCE     v   CREATE SEQUENCE public.kullaniciseq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 1000
    CACHE 1;
 #   DROP SEQUENCE public.kullaniciseq;
       public          postgres    false            �            1259    16502    satisgecmis    TABLE     �   CREATE TABLE public.satisgecmis (
    depoid integer,
    unurid integer,
    adet integer,
    satilan text,
    tarih date
);
    DROP TABLE public.satisgecmis;
       public         heap    postgres    false            �            1259    16473    urun    TABLE     �   CREATE TABLE public.urun (
    urunid integer NOT NULL,
    urunadi text,
    alisfiyat double precision,
    satisfiyat double precision,
    marka text,
    model text,
    aciklama text
);
    DROP TABLE public.urun;
       public         heap    postgres    false            �            1259    16400    urunseq    SEQUENCE     r   CREATE SEQUENCE public.urunseq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 1000
    CACHE 1;
    DROP SEQUENCE public.urunseq;
       public          postgres    false            /          0    16497    adet 
   TABLE DATA           4   COPY public.adet (depoid, urunid, adet) FROM stdin;
    public          postgres    false    208    $       .          0    16481    depo 
   TABLE DATA           =   COPY public.depo (depoid, depoadi, depoaciklama) FROM stdin;
    public          postgres    false    207   O$       2          0    16509    kuldep 
   TABLE DATA           7   COPY public.kuldep (depoid, id, calisiyor) FROM stdin;
    public          postgres    false    211   �$       ,          0    16463 	   kullanici 
   TABLE DATA           A   COPY public.kullanici (id, ad, soyad, eposta, sifre) FROM stdin;
    public          postgres    false    205   �$       0          0    16502    satisgecmis 
   TABLE DATA           K   COPY public.satisgecmis (depoid, unurid, adet, satilan, tarih) FROM stdin;
    public          postgres    false    209   <%       -          0    16473    urun 
   TABLE DATA           ^   COPY public.urun (urunid, urunadi, alisfiyat, satisfiyat, marka, model, aciklama) FROM stdin;
    public          postgres    false    206   _%       ;           0    0    deposeq    SEQUENCE SET     5   SELECT pg_catalog.setval('public.deposeq', 9, true);
          public          postgres    false    210            <           0    0    kullaniciseq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.kullaniciseq', 7, true);
          public          postgres    false    203            =           0    0    urunseq    SEQUENCE SET     5   SELECT pg_catalog.setval('public.urunseq', 7, true);
          public          postgres    false    204            �
           2606    16501    adet adet_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.adet
    ADD CONSTRAINT adet_pkey PRIMARY KEY (depoid, urunid);
 8   ALTER TABLE ONLY public.adet DROP CONSTRAINT adet_pkey;
       public            postgres    false    208    208            �
           2606    16488    depo depo_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.depo
    ADD CONSTRAINT depo_pkey PRIMARY KEY (depoid);
 8   ALTER TABLE ONLY public.depo DROP CONSTRAINT depo_pkey;
       public            postgres    false    207            �
           2606    16513    kuldep kuldep_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.kuldep
    ADD CONSTRAINT kuldep_pkey PRIMARY KEY (depoid, id);
 <   ALTER TABLE ONLY public.kuldep DROP CONSTRAINT kuldep_pkey;
       public            postgres    false    211    211            �
           2606    16470    kullanici kullanici_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.kullanici
    ADD CONSTRAINT kullanici_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.kullanici DROP CONSTRAINT kullanici_pkey;
       public            postgres    false    205            �
           2606    16480    urun urun_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.urun
    ADD CONSTRAINT urun_pkey PRIMARY KEY (urunid);
 8   ALTER TABLE ONLY public.urun DROP CONSTRAINT urun_pkey;
       public            postgres    false    206            �
           2620    24576    depo depotrg    TRIGGER     i   CREATE TRIGGER depotrg BEFORE INSERT ON public.depo FOR EACH ROW EXECUTE FUNCTION public.depofunction();
 %   DROP TRIGGER depotrg ON public.depo;
       public          postgres    false    207    214            �
           2620    16471    kullanici kullanicitrg    TRIGGER     x   CREATE TRIGGER kullanicitrg BEFORE INSERT ON public.kullanici FOR EACH ROW EXECUTE FUNCTION public.kullanicifunction();
 /   DROP TRIGGER kullanicitrg ON public.kullanici;
       public          postgres    false    205    212            �
           2620    24577    urun uruntrg    TRIGGER     i   CREATE TRIGGER uruntrg BEFORE INSERT ON public.urun FOR EACH ROW EXECUTE FUNCTION public.urunfunction();
 %   DROP TRIGGER uruntrg ON public.urun;
       public          postgres    false    206    213            /      x�3�4�4�2�4�42 RƜ�\1z\\\ *�2      .   5   x�3�tI�K�M5�L,NI㲀r�!\3�0����L�I-*Q�
r��qqq {��      2   #   x�3�4�,�2�4�&@Ҝ�HZp��=... fV�      ,   e   x�3�LI�K�ME��s3s���s�\�P�:��Ԉ��2F�1�s�r&� c.38��C�9�9���9G6���X����KN��,3��"�b���� �\Wq      0      x����A �=... 1�)      -   U   x�3�LI�K�M5�44�42���h.ΤĜ#�9�0�8��L9�ss1��8��)�6�L�I-*Q K �M�Z��b���� ��+I     