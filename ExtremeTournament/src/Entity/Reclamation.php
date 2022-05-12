<?php

namespace App\Entity;

use App\Repository\ReclamationRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=ReclamationRepository::class)
 */
class Reclamation
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id_reclam;
    /**
     * @ORM\Column(type="string", length=150)
     */
    private $descriptionR;

    /**
     * @ORM\Column(type="string", length=50)
     */
    private $type;

    /**
     * @ORM\Column(type="string", length=50)
     */
    private $etatR;

    /**
     * @ORM\Column(type="string", length=50)
     */
    private $email;

    /**
     * @ORM\Column(type="datetime")
     */
    private $dateR;

    /**
     * @ORM\ManyToOne(targetEntity=User::class, inversedBy="reclamations")
     * @ORM\JoinColumn(name="id_user",referencedColumnName="id_user",nullable=false)
     */
    private $id_user;


    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdReclam(): ?int
    {
        return $this->id_reclam;
    }

    public function setIdReclam(int $id_reclam): self
    {
        $this->id_reclam = $id_reclam;

        return $this;
    }

    public function getDescriptionR(): ?string
    {
        return $this->descriptionR;
    }

    public function setDescriptionR(string $descriptionR): self
    {
        $this->descriptionR = $descriptionR;

        return $this;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(string $type): self
    {
        $this->type = $type;

        return $this;
    }

    public function getEtatR(): ?string
    {
        return $this->etatR;
    }

    public function setEtatR(string $etatR): self
    {
        $this->etatR = $etatR;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    public function getDateR(): ?\DateTimeInterface
    {
        return $this->dateR;
    }

    public function setDateR(\DateTimeInterface $dateR): self
    {
        $this->dateR = $dateR;

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
