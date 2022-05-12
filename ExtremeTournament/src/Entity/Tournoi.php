<?php

namespace App\Entity;

use App\Repository\TournoiRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=TournoiRepository::class)
 */
class Tournoi
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id_t;

    /**
     * @ORM\Column(type="string", length=50)
     */
    private $nomT;

    /**
     * @ORM\Column(type="string", length=50)
     */
    private $emplacementT;

    /**
     * @ORM\Column(type="date")
     */
    private $dateT;

    /**
     * @ORM\Column(type="integer")
     */
    private $id_user;

    /**
     * @ORM\OneToMany(targetEntity=Poule::class, mappedBy="idT", orphanRemoval=true)
     */
    private $poules;

    public function __construct()
    {
        $this->poules = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdT(): ?int
    {
        return $this->idT;
    }

    public function setIdT(int $idT): self
    {
        $this->idT = $idT;

        return $this;
    }

    public function getNomT(): ?string
    {
        return $this->nomT;
    }

    public function setNomT(string $nomT): self
    {
        $this->nomT = $nomT;

        return $this;
    }

    public function getEmplacementT(): ?string
    {
        return $this->emplacementT;
    }

    public function setEmplacementT(string $emplacementT): self
    {
        $this->emplacementT = $emplacementT;

        return $this;
    }

    public function getDateT(): ?\DateTimeInterface
    {
        return $this->dateT;
    }

    public function setDateT(\DateTimeInterface $dateT): self
    {
        $this->dateT = $dateT;

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

    /**
     * @return Collection<int, Poule>
     */
    public function getPoules(): Collection
    {
        return $this->poules;
    }

    public function addPoule(Poule $poule): self
    {
        if (!$this->poules->contains($poule)) {
            $this->poules[] = $poule;
            $poule->setIdT($this);
        }

        return $this;
    }

    public function removePoule(Poule $poule): self
    {
        if ($this->poules->removeElement($poule)) {
            // set the owning side to null (unless already changed)
            if ($poule->getIdT() === $this) {
                $poule->setIdT(null);
            }
        }

        return $this;
    }
}
