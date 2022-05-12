<?php

namespace App\Entity;

use App\Repository\PublicationRepository;
use Cassandra\Date;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;


/**
 * @ORM\Entity(repositoryClass=PublicationRepository::class)
 */
class Publication
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     * @Groups("post:read")
     */
    public $id_publication;

    /**
     * @Assert\NotBlank(message="Titre doit etre non vide")
     * @ORM\Column(type="string", length=50)
     * @Groups ("post:read")
     */
    private $titre;

    /**
     *  @Assert\NotBlank(message=" Status doit etre non vide")
     * @Assert\Length(
     *      min = 5,
     *      minMessage=" Entrer un titre au mini de 5 caracteres"
     *
     *     )
     * @ORM\Column(type="string", length=150)
     * @Groups ("post:read")
     */
    private $status;

    /**
     * @ORM\Column(type="date")
     * @Groups ("post:read")
     */
    private $dateCreation ;


    /**
     * @ORM\ManyToOne(targetEntity=User::class, inversedBy="publications")
     * @ORM\JoinColumn(name="id_user",referencedColumnName="id_user",nullable=false)
     * @Groups ("post:read")
     */
    private $id_user;

    /**
     * @ORM\Column(type="string", length=100)
     * @Groups ("post:read")
     */
    private $image;

    /**
     * @Assert\NotBlank(message=" titre doit etre non vide")
     * @ORM\OneToMany(targetEntity=Commentaire::class, mappedBy="id_publication")
     * @Groups ("post:read")
     */
    public $commentaires;

    /**
     * @ORM\OneToMany(targetEntity=Likess::class, mappedBy="id_publication", orphanRemoval=true)
     * @Groups ("post:read")
     */
    private $likesses;

    /**
     * @ORM\OneToMany(targetEntity=Forum::class, mappedBy="id_publication", orphanRemoval=true)
     */
    private $forums;

    public function __construct()
    {
        $this->commentaires = new ArrayCollection();
        $this->likesses = new ArrayCollection();
        $this->forums = new ArrayCollection();
    }

    public function getid_publication(): ?int
    {
        return $this->id_publication;
    }

    public function getTitre(): ?string
    {
        return $this->titre;
    }

    public function setTitre(string $titre): self
    {
        $this->titre = $titre;

        return $this;
    }

    public function getStatus(): ?string
    {
        return $this->status;
    }

    public function setStatus(string $status): self
    {
        $this->status = $status;

        return $this;
    }


    public function getDateCreation(): ?\DateTimeInterface
    {
        return $this->dateCreation;
    }

    public function setDateCreation(\DateTimeInterface $dateCreation): self
    {
        $this->dateCreation = $dateCreation;

        return $this;
    }

    public function getIdUser(): ?user
    {
        return $this->id_user;
    }

    public function setIdUser(?user $id_user): self
    {
        $this->id_user = $id_user;

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
    public function __toString()
    {
        return (string)$this->id_publication;
    }
    /**
     * @return Collection<int, Commentaire>
     */
    public function getCommentaires(): Collection
    {
        return $this->commentaires;
    }

    public function addCommentaire(Commentaire $commentaire): self
    {
        if (!$this->commentaires->contains($commentaire)) {
            $this->commentaires[] = $commentaire;
            $commentaire->addIdPublication($this);
        }

        return $this;
    }

    public function removeCommentaire(Commentaire $commentaire): self
    {
        if ($this->commentaires->removeElement($commentaire)) {
            $commentaire->removeIdPublication($this);
        }

        return $this;
    }



    /**
     * @return Collection<int, Likess>
     */
    public function getLikesses(): Collection
    {
        return $this->likesses;
    }

    public function addLikess(Likess $likess): self
    {
        if (!$this->likesses->contains($likess)) {
            $this->likesses[] = $likess;
            $likess->setIdPublication($this);
        }

        return $this;
    }

    public function removeLikess(Likess $likess): self
    {
        if ($this->likesses->removeElement($likess)) {
            // set the owning side to null (unless already changed)
            if ($likess->getIdPublication() === $this) {
                $likess->setIdPublication(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection<int, Forum>
     */
    public function getForums(): Collection
    {
        return $this->forums;
    }

    public function addForum(Forum $forum): self
    {
        if (!$this->forums->contains($forum)) {
            $this->forums[] = $forum;
            $forum->setIdPublication($this);
        }

        return $this;
    }

    public function removeForum(Forum $forum): self
    {
        if ($this->forums->removeElement($forum)) {
            // set the owning side to null (unless already changed)
            if ($forum->getIdPublication() === $this) {
                $forum->setIdPublication(null);
            }
        }

        return $this;
    }
}
