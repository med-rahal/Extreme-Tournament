<?php

namespace App\Entity;

use App\Repository\MatchsRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=MatchsRepository::class)
 */
class Matchs
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id_match;

    /**
     * @ORM\Column(type="string", length=50)
     */
    private $nom_equipeA;

    /**
     * @ORM\Column(type="string", length=50)
     */
    private $nom_equipeB;

    /**
     * @ORM\Column(type="date")
     */
    private $date_match;

    /**
     * @ORM\Column(type="string", length=50)
     */
    private $emplacement;

    /**
     * @ORM\ManyToOne(targetEntity=Poule::class, inversedBy="matchs")
     * @ORM\JoinColumn(name="nom_poule",referencedColumnName="nom_poule",nullable=false)
     */
    private $poules;

    /**
     * @ORM\OneToMany(targetEntity=Equipe::class, mappedBy="matches", orphanRemoval=true)
     */
    private $equipes;




    public function __construct()
    {
        $this->matchs = new ArrayCollection();
        $this->equipes = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdMatch(): ?int
    {
        return $this->id_match;
    }

    public function setIdMatch(int $id_match): self
    {
        $this->id_match = $id_match;

        return $this;
    }

    public function getNomEquipeA(): ?string
    {
        return $this->nom_equipeA;
    }

    public function setNomEquipeA(string $nom_equipeA): self
    {
        $this->nom_equipeA = $nom_equipeA;

        return $this;
    }

    public function getNomEquipeB(): ?string
    {
        return $this->nom_equipeB;
    }

    public function setNomEquipeB(string $nom_equipeB): self
    {
        $this->nom_equipeB = $nom_equipeB;

        return $this;
    }

    public function getDateMatch(): ?\DateTimeInterface
    {
        return $this->date_match;
    }

    public function setDateMatch(\DateTimeInterface $date_match): self
    {
        $this->date_match = $date_match;

        return $this;
    }

    public function getEmplacement(): ?string
    {
        return $this->emplacement;
    }

    public function setEmplacement(string $emplacement): self
    {
        $this->emplacement = $emplacement;

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

    public function getPoules(): ?poule
    {
        return $this->poules;
    }

    public function setPoules(?poule $poules): self
    {
        $this->poules = $poules;

        return $this;
    }

    /**
     * @return Collection<int, Equipe>
     */
    public function getEquipes(): Collection
    {
        return $this->equipes;
    }

    public function addEquipe(Equipe $equipe): self
    {
        if (!$this->equipes->contains($equipe)) {
            $this->equipes[] = $equipe;
            $equipe->setMatches($this);
        }

        return $this;
    }

    public function removeEquipe(Equipe $equipe): self
    {
        if ($this->equipes->removeElement($equipe)) {
            // set the owning side to null (unless already changed)
            if ($equipe->getMatches() === $this) {
                $equipe->setMatches(null);
            }
        }

        return $this;
    }
}
