<?php

namespace App\Entity;

use App\Repository\PouleRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=PouleRepository::class)
 */
class Poule
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="string", length=50)
     */
    private $nom_poule ;

    /**
     * @ORM\ManyToOne(targetEntity=Tournoi::class, inversedBy="poules")
     * @ORM\JoinColumn(name="idT",referencedColumnName="id_t",nullable=false)
     */
    private $id_t;

    /**
     * @ORM\OneToMany(targetEntity=Matchs::class, mappedBy="poules", orphanRemoval=true)
     */
    private $matchs;

    public function __construct()
    {
        $this->matchs = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNomPoule(): ?string
    {
        return $this->nomPoule;
    }

    public function setNomPoule(string $nomPoule): self
    {
        $this->nomPoule = $nomPoule;

        return $this;
    }

    public function getNomEquipe(): ?string
    {
        return $this->nom_equipe;
    }

    public function setNomEquipe(string $nom_equipe): self
    {
        $this->nom_equipe = $nom_equipe;

        return $this;
    }

    public function getIdT(): ?tournoi
    {
        return $this->idT;
    }

    public function setIdT(?tournoi $idT): self
    {
        $this->idT = $idT;

        return $this;
    }

    /**
     * @return Collection<int, Matchs>
     */
    public function getMatchs(): Collection
    {
        return $this->matchs;
    }

    public function addMatch(Matchs $match): self
    {
        if (!$this->matchs->contains($match)) {
            $this->matchs[] = $match;
            $match->setPoules($this);
        }

        return $this;
    }

    public function removeMatch(Matchs $match): self
    {
        if ($this->matchs->removeElement($match)) {
            // set the owning side to null (unless already changed)
            if ($match->getPoules() === $this) {
                $match->setPoules(null);
            }
        }

        return $this;
    }
}
