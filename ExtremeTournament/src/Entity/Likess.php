<?php

namespace App\Entity;

use App\Repository\LikessRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=LikessRepository::class)
 */
class Likess
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id_like;

    /**
     * @ORM\ManyToOne(targetEntity=Publication::class, inversedBy="likesses")
     * @ORM\JoinColumn(name="id_publication",referencedColumnName="id_publication",nullable=false)
     */
    private $id_publication;

    /**
     * @ORM\Column(type="integer")
     */
    private $id_user;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdPublication(): ?publication
    {
        return $this->id_publication;
    }

    public function setIdPublication(?publication $id_publication): self
    {
        $this->id_publication = $id_publication;

        return $this;
    }

    public function getIdUser(): ?int
    {
        return $this->id_user;
    }

    public function setIdUser(int $id_user): self
    {
        $this->id_user = $id_user;

        return $this;
    }
}
