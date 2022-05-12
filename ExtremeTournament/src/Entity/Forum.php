<?php

namespace App\Entity;

use App\Repository\ForumRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=ForumRepository::class)
 */
class Forum
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id_forum;

    /**
     * @ORM\Column(type="string", length=150)
     */
    private $descriptionF;

    /**
     * @ORM\Column(type="string", length=50)
     */
    private $email;

    /**
     * @ORM\Column(type="integer")
     */
    private $id_commentaire;

    /**
     * @ORM\ManyToOne(targetEntity=Publication::class, inversedBy="forums")
     * @ORM\JoinColumn(name="id_publication",referencedColumnName="id_publication",nullable=false)
     */
    private $id_publication;



    public function __construct()
    {
        $this->id_publication = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getDescriptionF(): ?string
    {
        return $this->descriptionF;
    }

    public function setDescriptionF(string $descriptionF): self
    {
        $this->descriptionF = $descriptionF;

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

    public function getIdPublication(): ?int
    {
        return $this->id_publication;
    }

    public function setIdPublication(int $id_publication): self
    {
        $this->id_publication = $id_publication;

        return $this;
    }

    public function getIdCommentaire(): ?int
    {
        return $this->id_commentaire;
    }

    public function setIdCommentaire(int $id_commentaire): self
    {
        $this->id_commentaire = $id_commentaire;

        return $this;
    }

    public function addIdPublication(publication $idPublication): self
    {
        if (!$this->id_publication->contains($idPublication)) {
            $this->id_publication[] = $idPublication;
            $idPublication->setForum($this);
        }

        return $this;
    }

    public function removeIdPublication(publication $idPublication): self
    {
        if ($this->id_publication->removeElement($idPublication)) {
            // set the owning side to null (unless already changed)
            if ($idPublication->getForum() === $this) {
                $idPublication->setForum(null);
            }
        }

        return $this;
    }
}
