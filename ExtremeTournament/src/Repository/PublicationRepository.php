<?php

namespace App\Repository;

use App\Entity\Commentaire;
use App\Entity\Publication;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\OptimisticLockException;
use Doctrine\ORM\ORMException;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Publication|null find($id, $lockMode = null, $lockVersion = null)
 * @method Publication|null findOneBy(array $criteria, array $orderBy = null)
 * @method Publication[]    findAll()
 * @method Publication[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class PublicationRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Publication::class);
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function add(Publication $entity, bool $flush = true): void
    {
        $this->_em->persist($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    public function nbrcommentparid()
    {
        /*


                $query=$entitymanager->createQuery(' SELECT p
            FROM App\Entity\Publication p
            JOIN p.commentaires c

        ');
        return $query->getResult();
        */
        $em = $this->getEntityManager();

        $query = $em->createQuery('SELECT  count(p) FROM App\Entity\Publication p WHERE p.id_publication=:id' );
        return $query->setMaxResults(3)->getResult();


    }
    public function triparcomment(){

        $em=$this->getEntityManager();

        $query = $em->createQuery('SELECT  p FROM App\Entity\Publication p  LEFT JOIN  p.commentaires c GROUP BY p.id_publication ORDER BY count(c.id_publication) desc  ');
        return $query->setMaxResults(3)->getResult();
    }
    public function nbrparcomment(){
        /*


                $query=$entitymanager->createQuery(' SELECT p
            FROM App\Entity\Publication p
            JOIN p.commentaires c

        ');
        return $query->getResult();
        */
        $em=$this->getEntityManager();

        $query = $em->createQuery('SELECT count (p) FROM App\Entity\Publication p  LEFT JOIN  p.commentaires c GROUP BY p.id_publication ORDER BY count(c.id_publication) desc  ');
        return $query->setMaxResults(3)->getScalarResult();
    }

    public function findPublicationByTitle(string $query)
    {
        $qb = $this->createQueryBuilder('p');
        $qb
            ->where(
                $qb->expr()->andX(
                    $qb->expr()->orX(
                        $qb->expr()->like('p.titre', ':query')
                    )
                )
            )
            ->setParameter('query', '%' . $query . '%')
        ;
        return $qb
            ->getQuery()
            ->getResult();
    }



    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function remove(Publication $entity, bool $flush = true): void
    {
        $this->_em->remove($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    // /**
    //  * @return Publication[] Returns an array of Publication objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('p.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Publication
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
