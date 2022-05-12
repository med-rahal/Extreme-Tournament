<?php

namespace App\Entity;

use App\Repository\EquipeRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=EquipeRepository::class)
 */
class Equipe
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     *  @ORM\Column(type="string", length=50)
     */
    private $nom_equipe;
    /**
     * @ORM\Column(type="integer")
     */
    private $id_user;

    /**
     * @ORM\Column(type="integer")
     */
    private $nb_participants;

    /**
     * @ORM\Column(type="string", length=200)
     */
    private $image;

    /**
     * @ORM\Column(type="string", length=40)
     */
    private $categorie;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $Epass;

    /**
     * @ORM\ManyToOne(targetEntity=Matchs::class, inversedBy="equipes")
     * @ORM\JoinColumn(name="id_match",referencedColumnName="id_match",nullable=false)
     */
    private $matches;


    public function __construct()
    {
        $this->equipes = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
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

    public function getIdUser(): ?int
    {
        return $this->id_user;
    }

    public function setIdUser(int $id_user): self
    {
        $this->id_user = $id_user;

        return $this;
    }

    public function getNbParticipants(): ?int
    {
        return $this->nb_participants;
    }

    public function setNbParticipants(int $nb_participants): self
    {
        $this->nb_participants = $nb_participants;

        return $this;
    }

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(string $image): self
    {
        $this->image = $image;

        return $this;
    }

    public function getCategorie(): ?string
    {
        return $this->categorie;
    }

    public function setCategorie(string $categorie): self
    {
        $this->categorie = $categorie;

        return $this;
    }

    public function getEpass(): ?string
    {
        return $this->Epass;
    }

    public function setEpass(string $Epass): self
    {
        $this->Epass = $Epass;

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

    public function getMatches(): ?matchs
    {
        return $this->matches;
    }

    public function setMatches(?matchs $matches): self
    {
        $this->matches = $matches;

        return $this;
    }
}
